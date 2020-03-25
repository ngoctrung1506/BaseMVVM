package tgo.lostandfound

import android.util.Log
import android.widget.FrameLayout
import base.mvvm.BaseActivity
import butterknife.BindView
import io.reactivex.disposables.Disposable
import tgo.lostandfound.model.User
import tgo.lostandfound.screen.main.MainScreenFragment
import tgo.lostandfound.screen.user.UserViewModel
import javax.inject.Inject


class MainActivity : BaseActivity<UserViewModel>() {

    @Inject
    lateinit var mUser: User

    @BindView(R.id.container_frame)
    lateinit var mContainerFr: FrameLayout

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
