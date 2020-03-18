package tgo.lostandfound.api.file

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface IFileUpload {

    //    @Multipart
    @Headers("Content-Type: image/*"/*, "Connection: keep-alive"*/)
    @POST("/")
    fun upload(@Body file: RequestBody): Call<ResponseBody>
}