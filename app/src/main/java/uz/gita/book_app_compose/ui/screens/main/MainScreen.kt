@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package uz.gita.book_app_compose.ui.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.book_app_compose.data.remote.response.BookData
import uz.gita.book_app_compose.ui.dialogs.DeleteDialog
import uz.gita.book_app_compose.ui.dialogs.ErrorDialog
import uz.gita.book_app_compose.ui.dialogs.MessageDialog
import uz.gita.book_app_compose.ui.screens.main.details.BookDetails
import uz.gita.book_app_compose.ui.theme.Bg_Color
import uz.gita.book_app_compose.ui.theme.Primary
import uz.gita.book_app_compose.utils.CustomProgressBar
import uz.gita.book_app_compose.utils.CustomSearchView
import uz.gita.book_app_compose.utils.HORIZONTAL_MARGIN_STD
import uz.gita.book_app_compose.utils.ROUNDED_CORNER

// Created by Jamshid Isoqov on 12/5/2022
class MainScreen : AndroidScreen() {

    private lateinit var bookData: BookData

    @Composable
    override fun Content() {
        val viewModel: MainViewModel = getViewModel<MainViewModelImpl>()
        MainScreenContent(
            uiState = viewModel.collectAsState().value,
            onEvenDispatcher = viewModel::onEventDispatcher,
            navigator = LocalNavigator.currentOrThrow
        )
        var errorState: String by remember { mutableStateOf("") }

        var messageState: String by remember { mutableStateOf("") }

        var deleteState: Boolean by remember { mutableStateOf(false) }

        viewModel.collectSideEffect {
            when (it) {
                is MyMainSideEffect.Error -> {
                    errorState = it.error
                }
                is MyMainSideEffect.Message -> {
                    messageState = it.message
                }
                is MyMainSideEffect.Delete -> {
                    bookData = it.bookData
                    deleteState = true
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
        if (deleteState) {
            DeleteDialog {
                if (it){
                    viewModel.onEventDispatcher(intent = MainIntent.Delete(bookData))
                }
                deleteState = false
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    uiState: MainUiState,
    onEvenDispatcher: (MainIntent) -> Unit,
    navigator: Navigator
) {

    var search by remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = Unit) {
        onEvenDispatcher.invoke(MainIntent.RefreshData)
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Bg_Color),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = Primary,
                contentColor = Color.White
            ) {
                Icon(Icons.Filled.Add, "Add book")
            }
        }) {
        Column(modifier = Modifier.fillMaxSize()) {
            CustomSearchView(
                hint = "Search for books",
                text = search,
                modifier = Modifier.fillMaxWidth()
            ) {
                search = it
                onEvenDispatcher.invoke(MainIntent.SearchBooks(it))
            }

            Spacer(modifier = Modifier.height(HORIZONTAL_MARGIN_STD))

            when (uiState) {
                is MainUiState.Loading -> CustomProgressBar(
                    progress = uiState.isLoading,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )
                is MainUiState.Success -> {
                    LazyColumn(modifier = Modifier.weight(1f)) {
                        items(uiState.myBooksList.size) {
                            val book = uiState.myBooksList[it]
                            MyBookItem(modifier = Modifier
                                .padding(4.dp)
                                .clickable {
                                    navigator.push(BookDetails(book))
                                }
                                .fillMaxWidth()
                                .background(
                                    Color.White,
                                    shape = RoundedCornerShape(size = ROUNDED_CORNER)
                                ),
                                bookData = book,
                                onFavourite = {
                                    onEvenDispatcher.invoke(
                                        MainIntent.ChangeFavourite(!book.fav, book.id)
                                    )
                                },
                                onEdit = {
                                    //Todo update navigator.push()
                                },
                                onDelete = { onEvenDispatcher.invoke(MainIntent.DeleteBook(book)) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    //MainScreenContent()
}