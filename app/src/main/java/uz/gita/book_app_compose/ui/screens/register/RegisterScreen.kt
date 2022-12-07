package uz.gita.book_app_compose.ui.screens.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.book_app_compose.data.remote.request.UserDto
import uz.gita.book_app_compose.ui.screens.login.LoginScreen
import uz.gita.book_app_compose.ui.screens.verify.VerifyScreen
import uz.gita.book_app_compose.ui.theme.Primary
import uz.gita.book_app_compose.utils.*

// Created by Jamshid Isoqov on 12/5/2022
class RegisterScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: RegisterViewModel = getViewModel<RegisterViewModelImpl>()
        RegisterScreenContent(
            registerUiState = viewModel.collectAsState().value,
            onEventDispatcher = viewModel::onEventDispatcher
        )
    }
}

@Composable
fun RegisterScreenContent(
    registerUiState: RegisterUiState,
    onEventDispatcher: (RegisterIntent) -> Unit
) {
    val navigator: Navigator = LocalNavigator.currentOrThrow

    if (registerUiState.openVerifyScreen) {
        navigator.push(VerifyScreen())
    }
    if (registerUiState.isLoading) {
        CustomProgressBar(progress = true, modifier = Modifier.fillMaxSize())
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var phone by remember { mutableStateOf("") }
        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }

        var phoneBool by remember { mutableStateOf(false) }
        var firstNameBool by remember { mutableStateOf(false) }
        var lastNameBool by remember { mutableStateOf(false) }
        var passwordBool by remember { mutableStateOf(false) }
        var confirmPasswordBool by remember { mutableStateOf(false) }

        val isEnabledRegister by remember {
            mutableStateOf(
                phoneBool && firstNameBool && lastNameBool &&
                        passwordBool && confirmPasswordBool
            )
        }
        CustomTopBar(
            title = "Register", modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        )

        CustomEditText(
            hint = "First name",
            text = firstName,
            modifier = Modifier
                .padding(
                    horizontal = HORIZONTAL_MARGIN_STD,
                    vertical = VERTICAL_MARGIN_STD
                )
                .fillMaxWidth()
        ) {
            firstName = it
            firstNameBool = it.length >= 5
        }

        CustomEditText(
            hint = "Last name",
            text = lastName,
            modifier = Modifier
                .padding(
                    horizontal = HORIZONTAL_MARGIN_STD,
                    vertical = VERTICAL_MARGIN_STD
                )
                .fillMaxWidth()
        ) {
            lastName = it
            lastNameBool = it.length >= 5
        }

        CustomEditText(
            hint = "Phone number",
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
            hint = "Password",
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

        CustomEditText(
            hint = "Confirm password",
            text = confirmPassword,
            modifier = Modifier
                .padding(
                    horizontal = HORIZONTAL_MARGIN_STD,
                    vertical = VERTICAL_MARGIN_STD
                )
                .fillMaxWidth()
        ) {
            confirmPassword = it
            confirmPasswordBool = it == password
        }
        Spacer(modifier = Modifier.weight(1f))

        MultiStyleText(
            text1 = "Are you already registered?",
            color1 = Color.DarkGray,
            text2 = "Login",
            color2 = Primary,
            modifier = Modifier
        ) {
            navigator.popUntilRoot()
            navigator.push(LoginScreen())
        }
        CustomAppBottomButton(
            text = "Register",
            modifier = Modifier
                .padding(
                    horizontal = HORIZONTAL_MARGIN_STD,
                    vertical = VERTICAL_MARGIN_STD
                )
                .fillMaxWidth(),
            isEnabled = isEnabledRegister
        ) {
            onEventDispatcher.invoke(RegisterIntent(UserDto(firstName, lastName, phone, password)))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreenContent(RegisterUiState()) {

    }
}