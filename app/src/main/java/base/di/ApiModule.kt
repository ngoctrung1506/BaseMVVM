package base.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tgo.lostandfound.api.meta.IMetaApi
import tgo.lostandfound.api.user.IUserApi
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    companion object {
        const val BASE_URL = "https://api.github.com/"
        const val TIME_OUT = 60L
    }

    @Provides
    @Singleton
    fun provideOKHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            // time out
            // add interceptor llike logging, cache, or else like token, ...
            // certificatePinner
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun providesRetrofit(
        moshiConverterFactory: MoshiConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttpClient)
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