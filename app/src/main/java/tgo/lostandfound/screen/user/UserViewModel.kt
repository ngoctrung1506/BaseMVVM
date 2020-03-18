package tgo.lostandfound.screen.user

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import base.api.Result
import base.di.MySelfApp
import base.mvvm.BaseViewModel
import tgo.lostandfound.api.user.UserInfo
import javax.inject.Inject

class UserViewModel(application: Application) : BaseViewModel(application) {


    var userInfo: MutableLiveData<Result<UserInfo>>? = MutableLiveData()

    var mediaTor = MediatorLiveData<UserInfo>()

    @Inject
    lateinit var userRepository: UserRepository

    init {
        MySelfApp.mRepositoryComponent.inject(this)
    }

    fun getUserByLoginInfo(login: String): MutableLiveData<Result<UserInfo>>? =
        userRepository.getUserByLoginInfo(login)

    fun getInfo(){
        var test: LiveData<UserInfo> = MutableLiveData<UserInfo>()
//        userRepository.getUserByLoginInfo(login)

//        mediaTor.addSource(test, )
    }


}