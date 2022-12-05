package uz.gita.book_app_compose.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.book_app_compose.data.remote.request.*
import uz.gita.book_app_compose.data.remote.response.BookData
import uz.gita.book_app_compose.data.remote.response.MessageData
import uz.gita.book_app_compose.data.remote.response.TokenData
import uz.gita.book_app_compose.data.remote.response.UserData
import uz.gita.book_app_compose.utils.ResultData

// Created by Jamshid Isoqov on 12/5/2022
interface BookRepository {

    fun register(userDto: UserDto): Flow<ResultData<TokenData>>

    fun verifyRegisterUser(codeDto: CodeDto): Flow<ResultData<TokenData>>

    fun loginUser(loginDto: LoginDto): Flow<ResultData<TokenData>>

    fun verifyLoginUser(codeDto: CodeDto): Flow<ResultData<TokenData>>

    fun addBook(bookDto: BookDto): Flow<ResultData<BookData>>

    suspend fun updateBook(updateBookDto: UpdateBookDto)

    fun deleteBook(singleDto: SingleDto): Flow<ResultData<MessageData>>

    fun getAllBooks(): Flow<ResultData<List<BookData>>>

    fun updateBookStatus(singleDto: SingleDto): Flow<ResultData<MessageData>>

    fun getAllUsers(): Flow<ResultData<List<UserData>>>

    fun getBooksByUser(userId: Int): Flow<ResultData<List<BookData>>>

    fun updateBookRate(likeDto: LikeDto): Flow<ResultData<MessageData>>
}