package tgo.lostandfound.screen.user

import android.util.Log
import androidx.lifecycle.Observer
import base.BaseActivity
import tgo.lostandfound.R

class UserInfoActivity : BaseActivity<UserViewModel>() {


    override fun initViewModelClass(): Class<UserViewModel>? {
        return UserViewModel::class.java
    }

    override fun getLayoutId() = R.layout.activity_user_info


    override fun onCreateLayout() {
        Log.d("Info1", "UserInfo create layout")
        mViewModel?.userInfo?.observe(this, Observer {
            Log.d("Info1", "UserInfo" + it.name + it.bio)
        })
    }


}
