package base.api

import androidx.lifecycle.MutableLiveData

class Result<T>(
    var data: T?,
    var error: String
)
