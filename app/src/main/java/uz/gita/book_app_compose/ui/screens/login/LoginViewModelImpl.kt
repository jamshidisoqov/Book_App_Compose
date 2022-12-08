package uz.gita.book_app_compose.ui.screens.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.book_app_compose.data.local.prefs.MySharedPref
import uz.gita.book_app_compose.data.remote.request.LoginDto
import uz.gita.book_app_compose.domain.repository.BookRepository
import uz.gita.book_app_compose.utils.getMessage
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl @Inject constructor(
    private val repository: BookRepository,
    private val mySharedPref: MySharedPref
) :
    LoginViewModel, ViewModel() {
    override val container: Container<LoginUiState, LoginSideEffect> =
        container(initialState = LoginUiState.Success())

    override fun onEventDispatcher(intent: LoginIntent) = intent {
        when (intent) {
            is LoginIntent.Login -> {
                reduce { LoginUiState.Success(isLoading = true) }
                repository.loginUser(LoginDto(phone = intent.phone, password = intent.password))
                    .collectLatest { result ->
                        reduce {  LoginUiState.Success(isLoading = false) }
                        result.onSuccess {
                            mySharedPref.phone = intent.phone
                            mySharedPref.token = it.token
                            reduce {
                                LoginUiState.Success(openMainScreen = true)
                            }
                        }.onMessage {
                            postSideEffect(LoginSideEffect.Message(it))
                        }.onError {
                            postSideEffect(LoginSideEffect.Error(it.getMessage()))
                        }
                    }
            }
        }
    }
}