package tgo.lostandfound.screen.user

import android.app.Application
import androidx.lifecycle.MutableLiveData
import base.BaseViewModel
import tgo.lostandfound.api.RetrofitService
import tgo.lostandfound.api.user.IUserApi
import tgo.lostandfound.api.user.UserInfo

class UserViewModel(application: Application) : BaseViewModel(application) {

    var userInfo: MutableLiveData<UserInfo>? = MutableLiveData<UserInfo>()
    var userRepository: UserRepository? = null
    var userApi: IUserApi? = null

    init {
        userApi = RetrofitService.createService(IUserApi::class.java)
        userApi?.let {
            userRepository = UserRepository(it)
        }
        userInfo = userRepository?.getUserByLoginInfo("trung")
    }

    fun getUserByLoginInfo(login: String) {
        userInfo = userRepository?.getUserByLoginInfo(login)
    }

}