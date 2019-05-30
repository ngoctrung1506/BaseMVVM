package base

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class BaseViewModel constructor(val app:Application) : AndroidViewModel(app){
}