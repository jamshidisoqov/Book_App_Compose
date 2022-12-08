package uz.gita.book_app_compose.ui.screens.main

import uz.gita.book_app_compose.data.remote.response.BookData
import uz.gita.book_app_compose.ui.screens.login.MySideEffect
import uz.gita.book_app_compose.utils.BaseViewModel

// Created by Jamshid Isoqov on 12/5/2022
interface MainViewModel : BaseViewModel<MainIntent, MainUiState, MyMainSideEffect>

sealed interface MainIntent {
    data class ChangeFavourite(val isFavourite: Boolean, val bookId: Int) : MainIntent
    data class DeleteBook(val bookData: BookData) : MainIntent
    data class SearchBooks(val query: String) : MainIntent
    data class Delete(val bookData: BookData) : MainIntent
    object RefreshData:MainIntent
}

sealed interface MainUiState {
    data class Loading(val isLoading:Boolean = false) : MainUiState
    data class Success(
        val myBooksList: List<BookData> = emptyList()
    ):MainUiState
}

sealed interface MyMainSideEffect {
    data class Message(val message: String) : MyMainSideEffect
    data class Error(val error: String) : MyMainSideEffect
    data class Delete(val bookData: BookData):MyMainSideEffect
}
