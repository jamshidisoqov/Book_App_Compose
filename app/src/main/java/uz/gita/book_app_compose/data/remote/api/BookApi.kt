package uz.gita.book_app_compose.data.remote.api

import retrofit2.Response
import retrofit2.http.*
import uz.gita.book_app_compose.data.remote.request.*
import uz.gita.book_app_compose.data.remote.response.BookData
import uz.gita.book_app_compose.data.remote.response.MessageData
import uz.gita.book_app_compose.data.remote.response.TokenData
import uz.gita.book_app_compose.data.remote.response.UserData

// Created by Jamshid Isoqov on 12/5/2022
interface BookApi {

    @POST("auth/sign-up")
    suspend fun register(@Body userDto: UserDto): Response<TokenData>

    @POST("auth/sign-up/verify")
    suspend fun verifyRegisterUser(@Body codeDto: CodeDto): Response<TokenData>

    @POST("auth/sign-in")
    suspend fun loginUser(@Body loginDto: LoginDto): Response<TokenData>

    @POST("auth/sign-in/verify")
    suspend fun verifyLoginUser(@Body codeDto: CodeDto): Response<TokenData>

    @POST("book")
    suspend fun addBook(@Body bookDto: BookDto): Response<BookData>

    @POST("book")
    suspend fun updateBook(@Body updateBookDto: UpdateBookDto)

    @HTTP(method = "DELETE", path = "/book", hasBody = true)
    suspend fun deleteBook(@Body singleDto: SingleDto): Response<MessageData>

    @GET("books")
    suspend fun getAllBooks(): Response<List<BookData>>

    @POST("book/change-fav")
    suspend fun updateBookStatus(@Body singleDto: SingleDto): Response<MessageData>

    @GET("books/users")
    suspend fun getAllUsers(): Response<List<UserData>>

    @GET("books/{userId}")
    suspend fun getBooksByUser(@Path("userId") userId: Int): Response<List<BookData>>

    @POST("book/rate")
    suspend fun updateBookRate(@Body likeDto: LikeDto): Response<MessageData>


}