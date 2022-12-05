package uz.gita.book_app_compose.ui.screens.splash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.book_app_compose.data.local.prefs.MySharedPref
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val mySharedPref: MySharedPref
) : SplashViewModel, ViewModel() {

    override val container: Container<SplashUiState, Nothing> = container(SplashUiState())

    init {
        intent {
            reduce {
                if (mySharedPref.token.isEmpty()) state.copy(isOpenLogin = true)
                else state.copy(isOPenMain = true)
            }
        }
    }

    override fun onEventDispatcher(intent: Unit) {}
}