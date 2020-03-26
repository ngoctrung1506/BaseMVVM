package tgo.lostandfound.di

import android.app.Application
import tgo.lostandfound.di.component.ApiComponent
import tgo.lostandfound.di.component.DaggerApiComponent
import tgo.lostandfound.di.component.DaggerRepositoryComponent
import tgo.lostandfound.di.component.RepositoryComponent
import tgo.lostandfound.di.module.ApiModule
import tgo.lostandfound.di.module.RepoModule

class MySelfApp : Application() {

    companion object {
        lateinit var mApiComponent: ApiComponent
        lateinit var mRepositoryComponent: RepositoryComponent
    }

    override fun onCreate() {
        super.onCreate()
        initApiComponent()
    }

    private fun initApiComponent() {
        mApiComponent = DaggerApiComponent.builder().apiModule(
            ApiModule()
        ).build()

        mRepositoryComponent = DaggerRepositoryComponent.builder().repoModule(
            RepoModule()
        ).build()
    }
}