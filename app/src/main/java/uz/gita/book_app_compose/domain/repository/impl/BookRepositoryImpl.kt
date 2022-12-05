package uz.gita.book_app_compose.domain.repository.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.book_app_compose.data.local.prefs.MySharedPref
import uz.gita.book_app_compose.data.remote.api.BookApi
import uz.gita.book_app_compose.data.remote.request.*
import uz.gita.book_app_compose.data.remote.response.BookData
import uz.gita.book_app_compose.data.remote.response.MessageData
import uz.gita.book_app_compose.data.remote.response.TokenData
import uz.gita.book_app_compose.data.remote.response.UserData
import uz.gita.book_app_compose.domain.repository.BookRepository
import uz.gita.book_app_compose.utils.ResultData
import uz.gita.book_app_compose.utils.func
import uz.gita.book_app_compose.utils.hasConnection
import uz.gita.book_app_compose.utils.noInternetConnection
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api: BookApi,
    private val mySharedPref: MySharedPref
) : BookRepository {
    override fun register(userDto: UserDto): Flow<ResultData<TokenData>> =
        flow {
            if (hasConnection()) {
                emit(api.register(userDto).func())
            } else {
                emit(noInternetConnection())
            }
        }

    override fun verifyRegisterUser(codeDto: CodeDto): Flow<ResultData<TokenData>> =
        flow {
            if (hasConnection()) {
                emit(api.verifyRegisterUser(codeDto).func())
            } else {
                emit(noInternetConnection())
            }
        }

    override fun loginUser(loginDto: LoginDto): Flow<ResultData<TokenData>> =
        flow {
            if (hasConnection()) {
                emit(api.loginUser(loginDto).func())
            } else {
                emit(noInternetConnection())
            }
        }

    override fun verifyLoginUser(codeDto: CodeDto): Flow<ResultData<TokenData>> =
        flow {
            if (hasConnection()) {
                emit(api.verifyLoginUser(codeDto).func())
            } else {
                emit(noInternetConnection())
            }
        }

    override fun addBook(bookDto: BookDto): Flow<ResultData<BookData>> =
        flow {
            if (hasConnection()) {
                emit(api.addBook(bookDto).func())
            } else {
                emit(noInternetConnection())
            }
        }

    override suspend fun updateBook(updateBookDto: UpdateBookDto) {
        api.updateBook(updateBookDto)
    }

    override fun deleteBook(singleDto: SingleDto): Flow<ResultData<MessageData>> =
        flow {
            if (hasConnection()) {
                emit(api.deleteBook(singleDto).func())
            } else {
                emit(noInternetConnection())
            }
        }

    override fun getAllBooks(): Flow<ResultData<List<BookData>>> =
        flow {
            if (hasConnection()) {
                emit(api.getAllBooks().func())
            } else {
                emit(noInternetConnection())
            }
        }

    override fun updateBookStatus(singleDto: SingleDto): Flow<ResultData<MessageData>> =
        flow {
            if (hasConnection()) {
                emit(api.updateBookStatus(singleDto).func())
            } else {
                emit(noInternetConnection())
            }
        }

    override fun getAllUsers(): Flow<ResultData<List<UserData>>> =
        flow {
            if (hasConnection()) {
                emit(api.getAllUsers().func())
            } else {
                emit(noInternetConnection())
            }
        }

    override fun getBooksByUser(userId: Int): Flow<ResultData<List<BookData>>> =
        flow {
            if (hasConnection()) {
                emit(api.getBooksByUser(userId).func())
            } else {
                emit(noInternetConnection())
            }
        }

    override fun updateBookRate(likeDto: LikeDto): Flow<ResultData<MessageData>> =
        flow {
            if (hasConnection()) {
                emit(api.updateBookRate(likeDto).func())
            } else {
                emit(noInternetConnection())
            }
        }
}