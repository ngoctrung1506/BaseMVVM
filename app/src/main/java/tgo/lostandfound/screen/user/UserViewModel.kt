package tgo.lostandfound.screen.user

import android.app.Application
import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import base.api.Result
import base.di.MySelfApp
import tgo.lostandfound.api.user.UserInfo
import javax.inject.Inject

class UserViewModel(application: Application) : BaseViewModel(application) {


    var userInfo: MutableLiveData<Result<UserInfo>>? = MutableLiveData()

    @Inject
    lateinit var userRepository: UserRepository

    init {
        MySelfApp.mRepositoryComponent.inject(this)
    }

    fun getUserByLoginInfo(login: String): MutableLiveData<Result<UserInfo>>? =
        userRepository.getUserByLoginInfo(login)


}