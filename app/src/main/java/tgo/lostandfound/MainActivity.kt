package tgo.lostandfound

import android.util.Log
import gooner.demo.mvvm.BaseActivity
import io.reactivex.disposables.Disposable
import tgo.lostandfound.model.User
import tgo.lostandfound.screen.main.MainScreenFragment
import tgo.lostandfound.screen.user.UserViewModel
import javax.inject.Inject


class MainActivity : BaseActivity<UserViewModel>() {

    @Inject
    lateinit var mUser: User

    lateinit var mDisposable: Disposable

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


    override fun onDestroy() {
        super.onDestroy()
        mDisposable.dispose()
        Log.d("User1", "onDestroy")

    }

}
