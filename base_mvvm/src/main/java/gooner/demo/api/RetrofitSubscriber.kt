package gooner.demo.api

import io.reactivex.subscribers.DisposableSubscriber
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class RetrofitSubscriber<T> : DisposableSubscriber<Result<T>>() {

    final override fun onComplete() {
    }

    final override fun onStart() {
        super.onStart()
    }

    override fun onNext(t: Result<T>?) {
        if (t?.data != null) {
            t.data?.let {
                onResult(it.asResult())
            }
        } else if (t?.error != null) {
            t.error?.let {
                handleError(it)
            }
        }
    }

    override fun onError(t: Throwable?) {
        t?.let {
            it.printStackTrace()
            onResult(t.asErrorResult())
        }
    }

    // catch more error here
    private fun handleError(t: Throwable) {
        t.printStackTrace()
        when (t) {
            is HttpException -> {
                onResult(
                    t.asErrorResult()/*.apply {
                    error = HttpError.HTTP_EXCEPTION
                }*/
                )
            }

            is UnknownHostException -> {
                onResult(
                    t.asErrorResult()/*.apply {
                    error = HttpError.UNKNOW_HOST_EXCEPTION
                }*/
                )
            }

            is SocketTimeoutException -> {
                onResult(
                    t.asErrorResult()/*.apply {
                    error = HttpError.SOCKET_TIME_OUT_EXCEPTION
                }*/
                )
            }
            else ->
                onResult(
                    t.asErrorResult()/*.apply {
                    error = HttpError.UNKNOWN_ERROR
                }*/
                )
        }

    }

    abstract fun onResult(result: Result<T>)

}