package tgo.lostandfound.api.user

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IUserApi {

//    @GET("users")
//    fun getAllUser(): Call<>

    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Call<UserInfo>
}