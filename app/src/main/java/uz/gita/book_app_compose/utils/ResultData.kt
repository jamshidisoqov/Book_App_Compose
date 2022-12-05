package uz.gita.book_app_compose.utils

sealed class ResultData<T> {
    data class Success<T>(val data: T) : ResultData<T>()
    data class Error<T>(val error: Throwable) : ResultData<T>()
    data class Message<T>(val message: String) : ResultData<T>()

    inline fun onSuccess(block: (T) -> Unit): ResultData<T> {
        if (this is Success<T>) block(this.data)
        return this
    }

    inline fun onError(block: (Throwable) -> Unit): ResultData<T> {
        if (this is Error) block(this.error)
        return this
    }

    inline fun onMessage(block: (String) -> Unit): ResultData<T> {
        if (this is Message) block(this.message)
        return this
    }
}