package base

import android.app.Application
import androidx.lifecycle.AndroidViewModel

open class BaseViewModel constructor(val app: Application) : AndroidViewModel(app) {
}