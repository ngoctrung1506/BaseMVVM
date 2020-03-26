package tgo.lostandfound.di.demo

import tgo.lostandfound.di.scope.ActivityScope
import dagger.Module
import dagger.Provides
import tgo.lostandfound.model.User

@Module
class DemoModule {

    @Provides
    @ActivityScope
    fun providesUser() = User()

}