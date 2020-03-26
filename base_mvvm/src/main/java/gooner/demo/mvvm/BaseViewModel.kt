package gooner.demo.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class BaseViewModel constructor(val app: Application) :
    AndroidViewModel(app) {

//    init {
//        MySelfApp.mRepositoryComponent.inject(BaseViewModel)
//
//    }

}