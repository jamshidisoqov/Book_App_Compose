package uz.gita.book_app_compose.ui.screens.splash

import uz.gita.book_app_compose.utils.BaseViewModel

// Created by Jamshid Isoqov on 12/5/2022
interface SplashViewModel : BaseViewModel<Unit, SplashUiState, Nothing>

data class SplashUiState(
    val isOpenLogin: Boolean = false,
    val isOPenMain: Boolean = false
)