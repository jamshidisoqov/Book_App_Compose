package uz.gita.book_app_compose.ui.screens.main.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.book_app_compose.data.remote.request.LikeDto
import uz.gita.book_app_compose.data.remote.response.BookData
import uz.gita.book_app_compose.domain.repository.BookRepository
import uz.gita.book_app_compose.ui.screens.login.MySideEffect
import uz.gita.book_app_compose.utils.getMessage
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModelImpl @Inject constructor(
    private val repository: BookRepository
) : BookDetailsViewModel, ViewModel() {

    private var bookData: BookData? = null

    override val container: Container<BookDetailUiState, MySideEffect> =
        container(BookDetailUiState())

    override fun setBookData(bookData: BookData)  = intent{
        this@BookDetailsViewModelImpl.bookData = bookData
        reduce { state.copy(bookData = bookData) }
    }

    override fun onEventDispatcher(intent: BookDetailsIntent) = intent {
        reduce { state.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateBookRate(LikeDto(bookId = intent.bookId, isLike = intent.isLike))
                .collectLatest { result ->
                    reduce { state.copy(isLoading = false) }
                    result.onSuccess {
                        val book =
                            if (intent.isLike) bookData?.copy(likeCount = bookData?.likeCount!! + 1)
                            else bookData?.copy(likeCount = bookData?.disLike!! + 1)
                        reduce { state.copy(bookData = book) }
                        postSideEffect(MySideEffect.Message(it.message))
                    }.onMessage {
                        postSideEffect(MySideEffect.Message(it))
                    }.onError {
                        postSideEffect(MySideEffect.Error(it.getMessage()))
                    }
                }
        }
    }
}