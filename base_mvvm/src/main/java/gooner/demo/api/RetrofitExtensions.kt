package gooner.demo.api

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException

// change from T -> Result T with error included
//fun <T> Call<T>.retrofitResponseToResult(): Call<Result<T>> {
//    return this.asResult()
//}

fun <T> T.asResult(): Result<T> {
    return Result(data = this, error = null)
}

fun <T> Throwable.asErrorResult(): Result<T> {
    return Result(data = null, error = this)
}

fun <T> Observable<T>.retrofitResponseToResult(): Observable<Result<T>> {
    return this
        // transfrom T -> Result<T>
        .map {
            it.asResult()
        }
        // return Result<T>, same as map above
        .onErrorReturn {
            if (it is HttpException || it is IOException) {
                return@onErrorReturn it.asErrorResult<T>()
            } else {
                throw it
            }
        }
}

fun <T> Observable<T>.doSt(): Flowable<Result<T>> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .retrofitResponseToResult()
        .toFlowable(BackpressureStrategy.LATEST)
}