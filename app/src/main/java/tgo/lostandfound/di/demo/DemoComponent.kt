package tgo.lostandfound.di.demo

import tgo.lostandfound.di.scope.ActivityScope
import dagger.Component
import tgo.lostandfound.MainActivity

@ActivityScope
@Component(modules = [DemoModule::class])
interface DemoComponent {

    fun inject(mainActivity: MainActivity)

}