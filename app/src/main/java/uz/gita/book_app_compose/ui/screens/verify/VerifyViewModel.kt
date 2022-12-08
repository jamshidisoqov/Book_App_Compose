package uz.gita.book_app_compose.ui.screens.verify

import uz.gita.book_app_compose.data.remote.request.CodeDto
import uz.gita.book_app_compose.ui.screens.login.MySideEffect
import uz.gita.book_app_compose.utils.BaseViewModel

// Created by Jamshid Isoqov on 12/8/2022

interface VerifyViewModel : BaseViewModel<VerifyIntent, VerifyUiState, MySideEffect>

data class VerifyIntent(
    val codeDto: CodeDto,
    val type:Int
)

data class VerifyUiState(
    val openMainScreen: Boolean = false,
    val isLoading: Boolean = false
)