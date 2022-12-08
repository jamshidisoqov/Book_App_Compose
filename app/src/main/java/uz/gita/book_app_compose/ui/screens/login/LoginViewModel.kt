package uz.gita.book_app_compose.ui.screens.login

import uz.gita.book_app_compose.utils.BaseViewModel

// Created by Jamshid Isoqov on 12/5/2022
interface LoginViewModel : BaseViewModel<LoginIntent, LoginUiState, LoginSideEffect>


sealed interface LoginIntent {
    data class Login(
        val phone: String,
        val password: String
    ) : LoginIntent
}

sealed interface LoginUiState {
    data class Progress(val isLoading: Boolean = false) : LoginUiState
    data class Success(
        val openMainScreen: Boolean = false,
        val isLoading: Boolean = false
    ) : LoginUiState
}

sealed interface LoginSideEffect {
    data class Message(val message: String) : LoginSideEffect
    data class Error(val error: String) : LoginSideEffect
}

sealed interface MySideEffect {
    data class Message(val message: String) : MySideEffect
    data class Error(val error: String) : MySideEffect
}