package uz.gita.book_app_compose.data.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// Created by Jamshid Isoqov on 12/2/2022
@Parcelize
data class BookData(
    val id: Int,
    val title: String,
    val author: String,
    val description: String,
    val pageCount: Int,
    val fav: Boolean = false,
    val isLike: Boolean? = null,
    val likeCount: Int = 0,
    val disLike: Int = 0
) : Parcelable
