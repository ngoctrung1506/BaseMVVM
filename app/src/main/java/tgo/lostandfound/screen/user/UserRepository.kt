package tgo.lostandfound.screen.user

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tgo.lostandfound.api.user.IUserApi
import tgo.lostandfound.api.user.UserInfo

class UserRepository(val userApi: IUserApi) {

    fun getUserByLoginInfo(login: String): MutableLiveData<UserInfo> {
        var data = MutableLiveData<UserInfo>()
        userApi.getUser(login).enqueue(object : Callback<UserInfo> {

            override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                // response == null
                // response.body == null
                response.body()?.let {
                    data.value = it
                }
            }

            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                data.value = null
            }


        })

        return data
    }

}