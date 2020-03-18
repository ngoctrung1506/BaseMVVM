package tgo.lostandfound.screen.user

import androidx.lifecycle.MutableLiveData
import base.api.Result
import base.api.RetrofitSubscriber
import base.api.doSt
import base.di.MySelfApp
import tgo.lostandfound.api.user.IUserApi
import tgo.lostandfound.api.user.UserInfo
import javax.inject.Inject

class UserRepository {

    @Inject
    lateinit var userApi: IUserApi

    init {
        MySelfApp.mApiComponent.inject(this)
    }


    fun getUserByLoginInfo(login: String): MutableLiveData<Result<UserInfo>> {

        var data = MutableLiveData<Result<UserInfo>>()

//        LiveDataReactiveStreams.fromPublisher(
        userApi.getUser(login)
            .doSt()
            .subscribeWith(object : RetrofitSubscriber<UserInfo>() {
                override fun onResult(result: Result<UserInfo>) {
                    data.value = result
                }
            })
//        )

        return data
    }
}

