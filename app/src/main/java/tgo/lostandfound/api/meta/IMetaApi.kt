package tgo.lostandfound.api.meta

import retrofit2.Call
import retrofit2.http.GET

interface IMetaApi {

    @GET("meta")
    fun getMeta(): Call<MetaData>
}