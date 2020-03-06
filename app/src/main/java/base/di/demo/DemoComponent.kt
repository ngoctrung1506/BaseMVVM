package base.di.demo

import base.di.scope.ActivityScope
import dagger.Component
import tgo.lostandfound.MainActivity

@ActivityScope
@Component(modules = [DemoModule::class])
interface DemoComponent {

    fun inject(mainActivity: MainActivity)

}