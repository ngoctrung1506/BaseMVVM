package gooner.demo.api

import androidx.lifecycle.Observer

abstract class BaseObserser<T> : Observer<Result<T>> {

    override fun onChanged(t: Result<T>?) {
        if (t?.data != null) {
            t.data?.let {
                onSuccess(it)
            }
        } else if (t?.error != null) {
            onFail(t.error.toString())
        }
    }

    abstract fun onFail(error: String)

    abstract fun onSuccess(data: T)
}