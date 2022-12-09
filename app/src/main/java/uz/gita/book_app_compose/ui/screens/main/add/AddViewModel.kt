package uz.gita.book_app_compose.ui.screens.main.add

import uz.gita.book_app_compose.utils.BaseViewModel

// Created by Jamshid Isoqov on 12/8/2022
interface AddViewModel : BaseViewModel<AddBookIntent, AddUiState, AddSideEffect>

data class AddBookIntent(
    val title: String,
    val author: String,
    val description: String,
    val pageCount: Int
)

data class AddUiState(val isLoading: Boolean = false)

sealed interface AddSideEffect {
    data class Message(val message: String) : AddSideEffect
    data class Error(val error: String) : AddSideEffect
    object Back : AddSideEffect
}