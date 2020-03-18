package base.di

import android.app.Application
import base.di.component.ApiComponent
import base.di.component.DaggerApiComponent
import base.di.component.DaggerRepositoryComponent
import base.di.component.RepositoryComponent
import base.di.module.ApiModule
import base.di.module.RepoModule

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
        mApiComponent = DaggerApiComponent.builder().apiModule(ApiModule()).build()
        mRepositoryComponent = DaggerRepositoryComponent.builder().repoModule(RepoModule()).build()
    }
}