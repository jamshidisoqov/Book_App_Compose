package uz.gita.book_app_compose.ui.screens.register

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
import uz.gita.book_app_compose.data.local.prefs.MySharedPref
import uz.gita.book_app_compose.domain.repository.BookRepository
import uz.gita.book_app_compose.ui.screens.login.MySideEffect
import uz.gita.book_app_compose.utils.getMessage
import javax.inject.Inject

@HiltViewModel
class RegisterViewModelImpl @Inject constructor(
    private val repository: BookRepository,
    private val mySharedPref: MySharedPref
) : RegisterViewModel, ViewModel() {

    override val container: Container<RegisterUiState, MySideEffect> = container(RegisterUiState())

    override fun onEventDispatcher(intent: RegisterIntent) = intent {
        val data = intent.userDto
        viewModelScope.launch(Dispatchers.IO) {
            reduce { state.copy(isLoading = true) }
            repository.register(data).collectLatest { result ->
                reduce { state.copy(isLoading = false) }
                result.onSuccess {
                    mySharedPref.phone = data.phone
                    mySharedPref.name = data.firstName
                    mySharedPref.token = it.token
                    reduce {
                        state.copy(openVerifyScreen = true)
                    }
                }.onMessage {
                    postSideEffect(MySideEffect.Message(it))
                }.onError {
                    postSideEffect(MySideEffect.Error(it.getMessage()))
                }
            }
        }
    }
}