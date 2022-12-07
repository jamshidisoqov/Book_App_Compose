package uz.gita.book_app_compose.ui.screens.register

import uz.gita.book_app_compose.data.remote.request.BookDto
import uz.gita.book_app_compose.data.remote.request.UserDto
import uz.gita.book_app_compose.ui.screens.login.MySideEffect
import uz.gita.book_app_compose.utils.BaseViewModel

// Created by Jamshid Isoqov on 12/7/2022
interface RegisterViewModel : BaseViewModel<RegisterIntent, RegisterUiState, MySideEffect>

data class RegisterIntent(
    val userDto: UserDto
)

data class RegisterUiState(
    val openVerifyScreen: Boolean = false,
    val isLoading: Boolean = false
)