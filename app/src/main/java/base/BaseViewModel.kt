package base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import tgo.lostandfound.R

abstract class BaseViewModel constructor(val app: Application) :
    AndroidViewModel(app) {


}