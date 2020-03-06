package tgo.lostandfound

import android.content.Intent
import android.util.Log
import android.widget.FrameLayout
import base.BaseActivity
import base.di.MySelfApp
import base.di.demo.DaggerDemoComponent
import butterknife.BindView
import tgo.lostandfound.model.User
import tgo.lostandfound.screen.main.MainScreenFragment
import tgo.lostandfound.screen.user.UserViewModel
import javax.inject.Inject


class MainActivity : BaseActivity<UserViewModel>() {
    override fun initViewModelClass(): Class<UserViewModel>? {
        return null
    }

    @Inject
    lateinit var mUser: User

    @BindView(R.id.container_frame)
    lateinit var mContainerFr: FrameLayout


    override fun getLayoutId(): Int {
        return R.layout.activity_container
    }

    override fun onCreateLayout() {
        DaggerDemoComponent.builder().build().inject(this)
        Log.d("User1", mUser.toString())
        replaceView(R.id.container_frame, MainScreenFragment())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("Path", "in Activity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("User1", "onDestroy")

    }


}
