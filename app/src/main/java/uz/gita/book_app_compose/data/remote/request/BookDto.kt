package uz.gita.book_app_compose.data.remote.request

// Created by Jamshid Isoqov on 12/2/2022
data class BookDto(
    val title: String,
    val author: String,
    val description: String,
    val pageCount: Int
)
