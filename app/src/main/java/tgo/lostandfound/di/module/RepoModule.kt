package tgo.lostandfound.di.module

import dagger.Module
import dagger.Provides
import tgo.lostandfound.screen.main.MainScreenRepo
import tgo.lostandfound.screen.user.UserRepository
import javax.inject.Singleton

@Module
class RepoModule {

    @Provides
    @Singleton
    fun provideUserRepository() = UserRepository()

    @Provides
    @Singleton
    fun provideMainScreenRepository() = MainScreenRepo()

}