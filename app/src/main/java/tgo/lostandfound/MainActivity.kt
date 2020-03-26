package tgo.lostandfound

import gooner.demo.mvvm.BaseActivity
import tgo.lostandfound.screen.main.MainScreenFragment
import tgo.lostandfound.screen.user.UserViewModel


class MainActivity : BaseActivity<UserViewModel>() {

    override fun getViewModel(): UserViewModel? = null

    override fun initViewModelClass(): Class<UserViewModel>? {
        return null
    }


    override fun getLayoutId(): Int = R.layout.activity_container

    override fun getContainerId(): Int = R.id.container_frame

    override fun onCreateLayout() {
        mScreenTransitionImp.addScreen(MainScreenFragment())
    }

    override fun observeViewModel() {
    }

}
