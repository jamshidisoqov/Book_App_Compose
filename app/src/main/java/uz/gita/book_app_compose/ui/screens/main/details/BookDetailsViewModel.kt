package uz.gita.book_app_compose.ui.screens.main.details

import uz.gita.book_app_compose.data.remote.response.BookData
import uz.gita.book_app_compose.ui.screens.login.MySideEffect
import uz.gita.book_app_compose.utils.BaseViewModel

// Created by Jamshid Isoqov on 12/8/2022

interface BookDetailsViewModel : BaseViewModel<BookDetailsIntent, BookDetailUiState, MySideEffect> {
    fun setBookData(bookData: BookData)
}

data class BookDetailsIntent(val isLike: Boolean, val bookId: Int)

data class BookDetailUiState(val isLoading: Boolean = false,val bookData: BookData? = null)