package uz.gita.book_app_compose.ui.screens.main.add

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
import uz.gita.book_app_compose.data.remote.request.BookDto
import uz.gita.book_app_compose.domain.repository.BookRepository
import uz.gita.book_app_compose.utils.getMessage
import javax.inject.Inject

@HiltViewModel
class AddViewModelImpl @Inject constructor(
    private val repository: BookRepository
) : AddViewModel, ViewModel() {

    override val container: Container<AddUiState, AddSideEffect> = container(AddUiState())

    override fun onEventDispatcher(intent: AddBookIntent) = intent {
        viewModelScope.launch(Dispatchers.IO) {
            reduce { state.copy(isLoading = true) }
            intent.apply {
                repository.addBook(BookDto(title, author, description, pageCount)).collectLatest {
                    reduce { state.copy(isLoading = false) }
                    it.onSuccess {
                        postSideEffect(AddSideEffect.Back)
                    }.onMessage { message ->
                        postSideEffect(AddSideEffect.Message(message = message))
                    }.onError { error ->
                        postSideEffect(AddSideEffect.Error(error = error.getMessage()))
                    }
                }
            }
        }
    }
}