package tgo.lostandfound.screen.user

import androidx.lifecycle.MutableLiveData
import gooner.demo.api.Result
import gooner.demo.api.RetrofitSubscriber
import gooner.demo.api.doSt
import tgo.lostandfound.api.user.IUserApi
import tgo.lostandfound.api.user.UserInfo
import tgo.lostandfound.di.MySelfApp
import javax.inject.Inject

class UserRepository {

    @Inject
    lateinit var userApi: IUserApi

    init {
        MySelfApp.mApiComponent.inject(this)
    }


    fun getUserByLoginInfo(login: String): MutableLiveData<gooner.demo.api.Result<UserInfo>> {

        var data = MutableLiveData<gooner.demo.api.Result<UserInfo>>()

//        LiveDataReactiveStreams.fromPublisher(
        userApi.getUser(login)
            .doSt()
            .subscribeWith(object : RetrofitSubscriber<UserInfo>() {
                override fun onResult(result: gooner.demo.api.Result<UserInfo>) {
                    data.value = result
                }
            })
//        )

        return data
    }
}

