package uz.gita.book_app_compose.ui.screens.verify

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
class VerifyViewModelImpl @Inject constructor(
    private val repository: BookRepository,
    private val mySharedPref: MySharedPref
) : VerifyViewModel,ViewModel() {
    override val container: Container<VerifyUiState, MySideEffect> = container(VerifyUiState())

    override fun onEventDispatcher(intent: VerifyIntent) = intent {
        viewModelScope.launch(Dispatchers.IO){
            reduce { state.copy(isLoading = true) }
            if(intent.type==1) {
                repository.verifyLoginUser(intent.codeDto).collectLatest {result->
                    reduce { state.copy(isLoading = false) }
                    result.onSuccess {
                        mySharedPref.token = it.token
                        reduce {
                            state.copy(openMainScreen = true)
                        }
                    }.onMessage {
                        postSideEffect(MySideEffect.Message(it))
                    }.onError {
                        postSideEffect(MySideEffect.Error(it.getMessage()))
                    }

                }
            }else{
                repository.verifyRegisterUser(intent.codeDto).collectLatest {result->
                    reduce { state.copy(isLoading = false) }
                    result.onSuccess {
                        mySharedPref.token = it.token
                        reduce {
                            state.copy(openMainScreen = true)
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
}