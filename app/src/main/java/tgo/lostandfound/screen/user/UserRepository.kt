package tgo.lostandfound.screen.user

import androidx.lifecycle.MutableLiveData
import base.api.BaseCallBack
import base.api.Result
import tgo.lostandfound.api.user.IUserApi
import tgo.lostandfound.api.user.UserInfo

class UserRepository(val userApi: IUserApi) {

    fun getUserByLoginInfo(login: String): MutableLiveData<Result<UserInfo>> {

        var data = MutableLiveData<Result<UserInfo>>()

        // ko loi tra ve ket qua
        // loi tra ve message loi

        userApi.getUser(login).enqueue(object : BaseCallBack<UserInfo>() {
            override fun onResult(result: Result<UserInfo>) {
                data.value = result
            }
        })

        return data
    }

}

