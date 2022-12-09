package uz.gita.book_app_compose.ui.screens.main.add

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.book_app_compose.ui.dialogs.ErrorDialog
import uz.gita.book_app_compose.ui.dialogs.MessageDialog
import uz.gita.book_app_compose.ui.screens.login.MySideEffect
import uz.gita.book_app_compose.utils.*

// Created by Jamshid Isoqov on 12/8/2022
class AddBookScreen : AndroidScreen() {
    @Composable
    override fun Content() {

        val viewModel:AddViewModel = getViewModel<AddViewModelImpl>()
        val uiState = viewModel.collectAsState().value

        AddBookScreenContent(uiState = uiState, onEventDispatcher = viewModel::onEventDispatcher)

        var errorState: String by remember { mutableStateOf("") }

        var messageState: String by remember { mutableStateOf("") }

        val navigator = LocalNavigator.currentOrThrow

        viewModel.collectSideEffect {
            when (it) {
                is AddSideEffect.Error -> {
                    errorState = it.error
                }
                is AddSideEffect.Message -> {
                    messageState = it.message
                }
                is AddSideEffect.Back->{
                    navigator.pop()
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

@Composable
fun AddBookScreenContent(uiState: AddUiState, onEventDispatcher: (AddBookIntent) -> Unit) {

    val navigator: Navigator = LocalNavigator.currentOrThrow

    CustomProgressBar(progress = uiState.isLoading, modifier = Modifier.fillMaxSize())

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var title by remember { mutableStateOf("") }
        var author by remember { mutableStateOf("") }
        var pageCount by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }

        var titleBool by remember { mutableStateOf(false) }
        var authorBool by remember { mutableStateOf(false) }
        var pageCountBool by remember { mutableStateOf(false) }
        var descriptionBool by remember { mutableStateOf(false) }

        CustomTopBarWithNavigate(
            title = "Add book", modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            navigator.pop()
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        )

        CustomEditText(
            hint = "Title",
            text = title,
            modifier = Modifier
                .padding(
                    horizontal = HORIZONTAL_MARGIN_STD,
                    vertical = VERTICAL_MARGIN_STD
                )
                .fillMaxWidth()
        ) {
            title = it
            titleBool = it.length >= 10
        }

        CustomEditText(
            hint = "Author",
            text = author,
            modifier = Modifier
                .padding(
                    horizontal = HORIZONTAL_MARGIN_STD,
                    vertical = VERTICAL_MARGIN_STD
                )
                .fillMaxWidth()
        ) {
            author = it
            authorBool = it.length >= 10
        }

        CustomEditText(
            hint = "Page count",
            text = pageCount,
            modifier = Modifier
                .padding(
                    horizontal = HORIZONTAL_MARGIN_STD,
                    vertical = VERTICAL_MARGIN_STD
                )
                .fillMaxWidth(),
            keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Number)
        ) {
            pageCount = it
            pageCountBool = it.isNotEmpty() && it.length < 8
        }
        CustomEditText(
            hint = "Description",
            text = description,
            modifier = Modifier
                .padding(
                    horizontal = HORIZONTAL_MARGIN_STD,
                    vertical = VERTICAL_MARGIN_STD
                )
                .fillMaxWidth(),
            singleLine = false
        ) {
            description = it
            descriptionBool = it.length >= 20
        }

        Spacer(modifier = Modifier.weight(1f))


        CustomAppBottomButton(
            text = "Add book",
            modifier = Modifier
                .padding(
                    horizontal = HORIZONTAL_MARGIN_STD,
                    vertical = VERTICAL_MARGIN_STD
                )
                .fillMaxWidth(),
            isEnabled = titleBool && authorBool &&
                    pageCountBool && descriptionBool
        ) {
            onEventDispatcher.invoke(
                AddBookIntent(
                    title = title,
                    author = author,
                    description = description,
                    pageCount = pageCount.toInt()
                )
            )
        }
    }


}

@Preview(showSystemUi = true)
@Composable
fun AddBookScreenPreview() {
    AddBookScreenContent(AddUiState()) {}
}
