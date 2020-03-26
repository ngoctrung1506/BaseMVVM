package tgo.lostandfound.di.component

import tgo.lostandfound.di.module.ApiModule
import dagger.Component
import tgo.lostandfound.screen.main.MainScreenRepo
import tgo.lostandfound.screen.user.UserRepository
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(userRepository: UserRepository)

    fun inject(userRepository: MainScreenRepo)

}