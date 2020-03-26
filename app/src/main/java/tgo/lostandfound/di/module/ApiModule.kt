package tgo.lostandfound.di.module

import android.util.Log
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import tgo.lostandfound.api.meta.IMetaApi
import tgo.lostandfound.api.user.IUserApi
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    companion object {
        const val BASE_URL = "https://api.github.com/"
        const val BASE_URL_1 = "http://ec2-18-178-166-41.ap-northeast-1.compute.amazonaws.com:8000"
        const val TIME_OUT = 60L
    }

    @Provides
    @Singleton
    fun provideOKHttpClient(
        tokenInterceptor: Interceptor, loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            // time out
            // add interceptor llike logging, cache, or else like token, ...
            // certificatePinner
//            .addInterceptor(tokenInterceptor)
            .addInterceptor(loggingInterceptor)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideTokenInterceptor(): Interceptor {
        return Interceptor {
            val initialRequest = it.request()
            val urlRequest = initialRequest.url().uri().toString()
            var request = if (urlRequest.startsWith("$BASE_URL")) {
                initialRequest.newBuilder()
                    .addHeader("Authorization", "Token here")
                    .build()
            } else {
                initialRequest.newBuilder()
                    .build()
            }
            val response = it.proceed(request)
            when (response.code()) {
                401 -> {

                }

                400 -> {

                }

                403 -> {
                }
            }

            return@Interceptor response
        }

    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Log.d(
                "Retrofit",
                message
            )
        })
        interceptor.level =
                /*if (BuildConfig.DEBUG)*/
            HttpLoggingInterceptor.Level.BODY /*else HttpLoggingInterceptor.Level.NONE*/
        return interceptor
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        moshiConverterFactory: MoshiConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(moshiConverterFactory)
            .build()

    @Provides
    fun provideMoshiConverterFactory() = MoshiConverterFactory.create()


    @Provides
    @Singleton
    fun providesUserApi(retrofit: Retrofit): IUserApi =
        retrofit.create(IUserApi::class.java)

    @Provides
    @Singleton
    fun providesMetaDataApi(retrofit: Retrofit): IMetaApi =
        retrofit.create(IMetaApi::class.java)

}