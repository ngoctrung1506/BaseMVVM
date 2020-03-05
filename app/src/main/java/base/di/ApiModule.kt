package base.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tgo.lostandfound.api.meta.IMetaApi
import tgo.lostandfound.api.user.IUserApi
import javax.inject.Singleton

@Module
class ApiModule {

    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun providesUserApi(retrofit: Retrofit): IUserApi =
        retrofit.create(IUserApi::class.java)

    @Provides
    @Singleton
    fun providesMetaDataApi(retrofit: Retrofit): IMetaApi =
        retrofit.create(IMetaApi::class.java)

}