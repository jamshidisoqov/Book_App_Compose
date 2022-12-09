package uz.gita.book_app_compose.ui.screens.main.update

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import uz.gita.book_app_compose.data.remote.response.BookData
import uz.gita.book_app_compose.utils.*

// Created by Jamshid Isoqov on 12/8/2022
class UpdateBookScreen(bookData: BookData) : AndroidScreen() {
    @Composable
    override fun Content() {

    }
}

@Composable
fun UpdateBookContent(
    uiState: UpdateUiState,
    bookData: BookData,
    onEventDispatcher: (UpdateBookIntent) -> Unit
) {
    val navigator: Navigator = LocalNavigator.currentOrThrow

    CustomProgressBar(progress = uiState.isLoading, modifier = Modifier.fillMaxSize())

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var title by remember { mutableStateOf(bookData.title) }
        var author by remember { mutableStateOf(bookData.author) }
        var pageCount by remember { mutableStateOf(bookData.pageCount.toString()) }
        var description by remember { mutableStateOf(bookData.description) }

        var titleBool by remember { mutableStateOf(false) }
        var authorBool by remember { mutableStateOf(false) }
        var pageCountBool by remember { mutableStateOf(false) }
        var descriptionBool by remember { mutableStateOf(false) }

        CustomTopBarWithNavigate(
            title = "Update book", modifier = Modifier
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
            text = pageCount,
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
            text = title,
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
            text = "Update book",
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
                UpdateBookIntent(
                    bookId = bookData.id,
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
fun UpdateBookPreview() {

}