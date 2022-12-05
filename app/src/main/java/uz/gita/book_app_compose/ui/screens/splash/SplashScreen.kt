package uz.gita.book_app_compose.ui.screens.splash

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
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.book_app_compose.R
import uz.gita.book_app_compose.ui.screens.login.LoginScreen
import uz.gita.book_app_compose.ui.screens.main.MainScreen

// Created by Jamshid Isoqov on 12/5/2022
class SplashScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: SplashViewModel = getViewModel<SplashViewModelImpl>()
        SplashScreenContent(viewModel.collectAsState().value) {
            val screen = when (it) {
                SplashScreenEnum.MAIN -> MainScreen()
                SplashScreenEnum.LOGIN -> LoginScreen()
            }
            navigator.push(screen)
        }
    }
}

@Composable
private fun SplashScreenContent(uiState: SplashUiState, block: (SplashScreenEnum) -> Unit) {
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

        LaunchedEffect(key1 = Unit) {
            delay(1200)
            if (uiState.isOPenMain) {
                block.invoke(SplashScreenEnum.MAIN)
            }
            if (uiState.isOpenLogin) {
                block.invoke(SplashScreenEnum.LOGIN)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    SplashScreenContent(SplashUiState()) {

    }
}

enum class SplashScreenEnum {
    MAIN, LOGIN
}