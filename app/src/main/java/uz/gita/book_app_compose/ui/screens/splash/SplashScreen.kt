@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package uz.gita.book_app_compose.ui.screens.splash

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.book_app_compose.R
import uz.gita.book_app_compose.ui.screens.login.LoginScreen
import uz.gita.book_app_compose.ui.screens.main.MainScreen

// Created by Jamshid Isoqov on 12/5/2022
class SplashScreen : Screen {

    private lateinit var navigator: Navigator

    @Composable
    override fun Content() {
        val viewModel: SplashViewModel = getViewModel<SplashViewModelImpl>()
        navigator = LocalNavigator.currentOrThrow
        SplashScreenContent(viewModel.collectAsState().value, navigator)
    }
}

@Composable
private fun SplashScreenContent(uiState: SplashUiState, navigator: Navigator) {

    Box(modifier = Modifier.fillMaxSize()) {

        val contentDescriptionImage by remember {
            mutableStateOf("Watpad book app")
        }

        Image(
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.watpad),
            contentDescription = contentDescriptionImage,
            contentScale = ContentScale.FillBounds
        )
        LaunchedEffect(key1 = uiState.isOPenMain||uiState.isOpenLogin) {
            Log.d("TTT", "SplashScreenContent:${uiState} ")
            if (uiState.isOPenMain) {
                navigator.replace(MainScreen())
            }
            if (uiState.isOpenLogin) {
                navigator.replace(LoginScreen())
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    SplashScreenContent(SplashUiState(), LocalNavigator.currentOrThrow)
}