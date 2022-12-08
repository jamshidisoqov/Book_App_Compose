package uz.gita.book_app_compose.ui.screens.login

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.book_app_compose.ui.screens.register.RegisterScreen
import uz.gita.book_app_compose.ui.screens.verify.VerifyScreen
import uz.gita.book_app_compose.ui.theme.Primary
import uz.gita.book_app_compose.utils.*

// Created by Jamshid Isoqov on 12/5/2022
class LoginScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: LoginViewModel = getViewModel<LoginViewModelImpl>()
        LoginScreenContent(
            uiState = viewModel.collectAsState().value,
            onEventDispatcher = viewModel::onEventDispatcher
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LoginScreenContent(uiState: LoginUiState, onEventDispatcher: (LoginIntent) -> Unit) {
    Log.d("TTT", "LoginScreenContent: Keldi")
    val navigator: Navigator = LocalNavigator.currentOrThrow



    when (uiState) {
        is LoginUiState.Success -> {
            CustomProgressBar(progress = uiState.isLoading, modifier = Modifier.fillMaxSize())
            LaunchedEffect(key1 = uiState.openMainScreen) {
                if (uiState.openMainScreen) {
                    navigator.push(VerifyScreen(1))
                }
            }
        }
        is LoginUiState.Progress -> {
            CustomProgressBar(progress = uiState.isLoading, modifier = Modifier.fillMaxSize())
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var phone by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        var phoneBool by remember { mutableStateOf(false) }
        var passwordBool by remember { mutableStateOf(false) }

        CustomTopBar(
            title = "Login", modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
        CustomEditText(
            hint = "Enter your phone number",
            text = phone,
            modifier = Modifier
                .padding(
                    horizontal = HORIZONTAL_MARGIN_STD,
                    vertical = VERTICAL_MARGIN_STD
                )
                .fillMaxWidth(),
            keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Phone)
        ) {
            phone = it
            phoneBool = it.length >= 13
        }
        CustomEditText(
            hint = "Enter your password",
            text = password,
            modifier = Modifier
                .padding(
                    horizontal = HORIZONTAL_MARGIN_STD,
                    vertical = VERTICAL_MARGIN_STD
                )
                .fillMaxWidth()
        ) {
            password = it
            passwordBool = it.length >= 5
        }
        Spacer(modifier = Modifier.weight(1f))

        MultiStyleText(
            text1 = "Not registered yet?",
            color1 = DarkGray,
            text2 = "Register",
            color2 = Primary,
            modifier = Modifier
        ) {
            navigator.replace(RegisterScreen())
        }
        CustomAppBottomButton(
            text = "Login",
            modifier = Modifier
                .padding(
                    horizontal = HORIZONTAL_MARGIN_STD,
                    vertical = VERTICAL_MARGIN_STD
                )
                .fillMaxWidth(),
            isEnabled = phoneBool && passwordBool
        ) {
            onEventDispatcher.invoke(LoginIntent.Login(phone, password))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreenContent(LoginUiState.Progress(isLoading = true)) {}
}

