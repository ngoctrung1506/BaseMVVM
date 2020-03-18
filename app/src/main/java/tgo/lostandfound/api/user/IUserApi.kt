package tgo.lostandfound.api.user

import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.http.GET
import retrofit2.http.Path

interface IUserApi {

//    @GET("users")
//    fun getAllUser(): Call<>

    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Observable<UserInfo>
}