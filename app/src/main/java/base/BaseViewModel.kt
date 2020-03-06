package base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import base.di.MySelfApp

abstract class BaseViewModel constructor(val app: Application) :
    AndroidViewModel(app) {

//    init {
//        MySelfApp.mRepositoryComponent.inject(BaseViewModel)
//
//    }

}