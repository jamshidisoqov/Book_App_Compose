package uz.gita.book_app_compose.utils

import com.google.gson.Gson
import retrofit2.Response
import uz.gita.book_app_compose.data.remote.response.MessageData


fun <T> Response<T>.func(): ResultData<T> {
    val data = this
    if (data.isSuccessful) {
        return if (data.body() != null) {
            val body = data.body()!!
            ResultData.Success(body)
        } else {
            ResultData.Error(Throwable("Body null"))
        }
    }
    val gson = Gson()
    val error = gson.fromJson(data.errorBody()?.string(), MessageData::class.java)
    return ResultData.Error(Throwable(error.message))
}
