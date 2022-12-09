package uz.gita.book_app_compose.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.book_app_compose.data.remote.request.SingleDto
import uz.gita.book_app_compose.data.remote.response.BookData
import uz.gita.book_app_compose.domain.repository.BookRepository
import uz.gita.book_app_compose.utils.getMessage
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val repository: BookRepository
) : MainViewModel, ViewModel() {

    private var allMyBooks: List<BookData> = emptyList()

    override val container: Container<MainUiState, MyMainSideEffect> =
        container(MainUiState.Loading(isLoading = true))

    override fun onEventDispatcher(intent: MainIntent) = intent {
        when (intent) {
            is MainIntent.ChangeFavourite -> {
                reduce { MainUiState.Loading(true) }
                repository.updateBookStatus(SingleDto(intent.bookId))
                getAllBooks()
            }

            is MainIntent.DeleteBook -> postSideEffect(MyMainSideEffect.Delete(intent.bookData))

            is MainIntent.SearchBooks -> {
                val list = allMyBooks.filter {
                    it.title.startsWith(intent.query) || it.author.startsWith(intent.query)
                }
                reduce { MainUiState.Success(myBooksList = list) }
            }
            is MainIntent.Delete -> {
                reduce { MainUiState.Loading(isLoading = true) }
                repository.deleteBook(SingleDto(intent.bookData.id)).collectLatest { result ->
                    result.onSuccess { getAllBooks() }
                        .onMessage {
                            postSideEffect(MyMainSideEffect.Message(it))
                            reduce { MainUiState.Loading(isLoading = false) }
                        }.onError {
                            reduce { MainUiState.Loading(isLoading = false) }
                            postSideEffect(MyMainSideEffect.Error(it.getMessage()))
                        }
                }
            }
            MainIntent.RefreshData -> {
                reduce { MainUiState.Loading(isLoading = true) }
                getAllBooks()
            }
        }
    }

    private fun getAllBooks() = intent {
        viewModelScope.launch {
            repository.getAllBooks().collectLatest { result ->
                result.onSuccess { books ->
                    allMyBooks = books
                    reduce { MainUiState.Success(myBooksList = books) }
                }.onMessage {
                    postSideEffect(MyMainSideEffect.Message(it))
                }.onError {
                    postSideEffect(MyMainSideEffect.Error(it.getMessage()))
                }
            }
        }
    }
}