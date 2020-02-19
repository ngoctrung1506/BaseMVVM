package tgo.lostandfound.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitService {

    companion object {
        const val BASE_URL = "https://api.github.com/"

        fun <S> createService(service: Class<S>) = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(service)
    }
}