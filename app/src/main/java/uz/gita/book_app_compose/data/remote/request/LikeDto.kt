package uz.gita.book_app_compose.data.remote.request

// Created by Jamshid Isoqov on 12/3/2022
data class LikeDto(
    val bookId:Int,
    val isLike:Boolean? = null
)