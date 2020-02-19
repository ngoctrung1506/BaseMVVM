package tgo.lostandfound

import android.content.Intent
import android.util.Log
import android.widget.FrameLayout
import base.BaseActivity
import butterknife.BindView
import tgo.lostandfound.screen.main.MainScreenFragment
import tgo.lostandfound.screen.user.UserViewModel


class MainActivity : BaseActivity<UserViewModel>() {
    override fun initViewModelClass(): Class<UserViewModel>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    @BindView(R.id.container_frame)
    lateinit var mContainerFr: FrameLayout


    override fun getLayoutId(): Int {
        return R.layout.activity_container
    }

    override fun onCreateLayout() {
        replaceView(R.id.container_frame, MainScreenFragment())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("Path", "in Activity")
    }


}
