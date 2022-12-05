package uz.gita.book_app_compose.utils

// Created by Jamshid Isoqov an 10/12/2022
fun Throwable.getMessage() = this.message ?: "Unknown error"
