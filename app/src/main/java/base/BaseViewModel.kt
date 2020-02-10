package base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import tgo.lostandfound.R

abstract class BaseViewModel constructor(val app: Application) :
    AndroidViewModel(app) {

    // add base repo

    protected var mRepository: R

    init {
        mRepository = initRepositoryClass().newInstance()
    }

    protected abstract fun initRepositoryClass(): Class<R>


}