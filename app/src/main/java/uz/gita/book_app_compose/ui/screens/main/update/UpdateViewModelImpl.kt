package uz.gita.book_app_compose.ui.screens.main.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.book_app_compose.data.remote.request.UpdateBookDto
import uz.gita.book_app_compose.domain.repository.BookRepository
import uz.gita.book_app_compose.utils.hasConnection
import javax.inject.Inject

@HiltViewModel
class UpdateViewModelImpl @Inject constructor(
    private val repository: BookRepository
) : UpdateViewModel, ViewModel() {
    override val container: Container<UpdateUiState, UpdateSideEffect> = container(UpdateUiState())

    override fun onEventDispatcher(intent: UpdateBookIntent) = intent {
        viewModelScope.launch(Dispatchers.IO) {
            reduce { state.copy(isLoading = true) }
            intent.apply {
                if (hasConnection()) {
                    repository.updateBook(
                        UpdateBookDto(
                            bookId,
                            title,
                            author,
                            description,
                            pageCount
                        )
                    )
                }else{
                    postSideEffect(UpdateSideEffect.Message("No internet connection"))
                }
                reduce { state.copy(isLoading = false) }
            }
        }
    }
}