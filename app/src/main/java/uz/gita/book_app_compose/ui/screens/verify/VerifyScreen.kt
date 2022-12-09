@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package uz.gita.book_app_compose.ui.screens.verify

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.book_app_compose.data.remote.request.CodeDto
import uz.gita.book_app_compose.ui.dialogs.ErrorDialog
import uz.gita.book_app_compose.ui.dialogs.MessageDialog
import uz.gita.book_app_compose.ui.screens.login.MySideEffect
import uz.gita.book_app_compose.ui.screens.main.MainScreen
import uz.gita.book_app_compose.ui.theme.Primary
import uz.gita.book_app_compose.utils.*

// Created by Jamshid Isoqov on 12/5/2022

class VerifyScreen(private val type: Int) : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: VerifyViewModel = getViewModel<VerifyViewModelImpl>()
        VerifyScreenContent(
            verifyUiState = viewModel.collectAsState().value,
            onEventDispatcher = viewModel::onEventDispatcher,
            type = type
        )

        var errorState: String by remember { mutableStateOf("") }

        var messageState: String by remember { mutableStateOf("") }

        viewModel.collectSideEffect {
            when (it) {
                is MySideEffect.Error -> {
                    errorState = it.error
                }
                is MySideEffect.Message -> {
                    messageState = it.message
                }
            }
        }

        if (errorState.isNotEmpty()) {
            ErrorDialog(error = errorState) {
                errorState = ""
            }
        }
        if (messageState.isNotEmpty()) {
            MessageDialog(message = messageState) {
                messageState = ""
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerifyScreenContent(
    verifyUiState: VerifyUiState,
    type: Int = 1,
    onEventDispatcher: (VerifyIntent) -> Unit
) {

    val navigator = LocalNavigator.currentOrThrow

    var code: String by remember {
        mutableStateOf("")
    }

    if (verifyUiState.isLoading) {
        CustomProgressBar(progress = true, modifier = Modifier.fillMaxSize())
    }
    LaunchedEffect(key1 = verifyUiState.openMainScreen) {
        if (verifyUiState.openMainScreen) {
            navigator.popUntilRoot()
            navigator.replace(MainScreen())
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {

        var isEnabled by remember { mutableStateOf(false) }

        CustomTopBar(
            title = "Confirm code",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .size(56.dp)
        )

        Spacer(
            modifier = Modifier
                .height(120.dp)
                .align(Alignment.CenterHorizontally)
        )

        CustomSmsCodeView(
            smsCodeLength = 6,
            textFieldColors = TextFieldDefaults.textFieldColors(),
            textStyle = MaterialTheme.typography.titleMedium
        ) {
            isEnabled = it.length == 6
            code = it
        }

        Spacer(modifier = Modifier.weight(1f))

        MultiStyleText(
            text1 = "Code expire date",
            color1 = Color.DarkGray,
            text2 = "00:39",
            color2 = Primary,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {

        }

        CustomAppBottomButton(
            text = "Confirm code",
            modifier = Modifier
                .padding(
                    horizontal = HORIZONTAL_MARGIN_STD,
                    vertical = VERTICAL_MARGIN_STD
                )
                .fillMaxWidth(),
            isEnabled = isEnabled
        ) {
            isEnabled = false
            onEventDispatcher.invoke(VerifyIntent(codeDto = CodeDto(code), type))
        }


    }
}

@Preview(showSystemUi = true)
@Composable
fun VerifyScreenPreview() {
    VerifyScreenContent(VerifyUiState()) {

    }
}