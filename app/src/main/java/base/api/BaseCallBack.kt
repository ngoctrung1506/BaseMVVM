package base.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseCallBack<T> : Callback<T> {

    override fun onFailure(call: Call<T>, t: Throwable) {
        // return exception base on throwable
        t.printStackTrace()
        when (t) {
            is HttpException -> {
                onResult(t.asErrorResult<T>()/*.apply {
                    error = HttpError.HTTP_EXCEPTION
                }*/)
            }

            is UnknownHostException -> {
                onResult(t.asErrorResult<T>()/*.apply {
                    error = HttpError.UNKNOW_HOST_EXCEPTION
                }*/)
            }

            is SocketTimeoutException -> {
                onResult(t.asErrorResult<T>()/*.apply {
                    error = HttpError.SOCKET_TIME_OUT_EXCEPTION
                }*/)
            }
            else ->
                onResult(t.asErrorResult<T>()/*.apply {
                    error = HttpError.UNKNOWN_ERROR
                }*/)
        }
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            response.body()?.let {
                onResult(it.asResult())
            }
        } else {
//            onResult(response.message().asResult())
        }
    }

    abstract fun onResult(result: Result<T>)


}