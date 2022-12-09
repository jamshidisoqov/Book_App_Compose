package uz.gita.book_app_compose.ui.screens.main.update

import uz.gita.book_app_compose.utils.BaseViewModel

// Created by Jamshid Isoqov on 12/8/2022
interface UpdateViewModel : BaseViewModel<UpdateBookIntent, UpdateUiState, UpdateSideEffect>

data class UpdateBookIntent(
    val bookId:Int,
    val title: String,
    val author: String,
    val description: String,
    val pageCount: Int
)

data class UpdateUiState(val isLoading: Boolean = false)

sealed interface UpdateSideEffect {
    data class Message(val message: String) : UpdateSideEffect
    data class Error(val error: String) : UpdateSideEffect
    object Back : UpdateSideEffect
}