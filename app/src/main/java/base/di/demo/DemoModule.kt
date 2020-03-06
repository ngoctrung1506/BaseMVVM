package base.di.demo

import base.di.scope.ActivityScope
import dagger.Module
import dagger.Provides
import tgo.lostandfound.model.User
import javax.inject.Singleton

@Module
class DemoModule {

    @Provides
    @ActivityScope
    fun providesUser() = User()

}