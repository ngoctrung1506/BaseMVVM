package base.api

// change from T -> Result T with error included
//fun <T> Call<T>.retrofitResponseToResult(): Call<Result<T>> {
//    return this.asResult()
//}

fun <T> T.asResult(): Result<T> {
    return Result(data = this, error = "")
}

fun <T> String.asResult(): Result<T> {
    return Result(data = null, error = this)
}

fun <T> Throwable.asErrorResult(): Result<T> {
    return Result(data = null, error = this.message ?: HttpError.UNKNOWN_ERROR)
}
