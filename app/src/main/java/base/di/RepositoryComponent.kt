package base.di

import base.BaseViewModel
import dagger.Component
import tgo.lostandfound.screen.main.MainScreenViewModel
import tgo.lostandfound.screen.user.UserViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [RepoModule::class])
interface RepositoryComponent {

    fun inject(userViewModel: UserViewModel)

    fun inject(userViewModel: MainScreenViewModel)

    fun inject(base: Class<out BaseViewModel>)

}