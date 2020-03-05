package base.di

import android.app.Application

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