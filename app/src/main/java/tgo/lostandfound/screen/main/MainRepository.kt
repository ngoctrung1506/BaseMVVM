package tgo.lostandfound.screen.main

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tgo.lostandfound.api.user.IUserApi
import tgo.lostandfound.api.user.UserInfo

class MainRepository(val userApi: IUserApi) {

//    fun getListItem(userId: String) = userApi.getUser(userId)?.enqueue(object : Callback<UserInfo>{
//        override fun onFailure(call: Call<UserInfo>, t: Throwable) {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        }
//
//        override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        }
//    })
}