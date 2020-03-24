package tgo.lostandfound.screen.user

import android.util.Log
import android.widget.EditText
import android.widget.TextView
import base.api.BaseObserser
import base.mvvm.BaseActivity
import kotlinx.android.synthetic.main.activity_user_info.*
import tgo.lostandfound.R
import tgo.lostandfound.api.user.UserInfo

class UserInfoActivity : BaseActivity<UserViewModel>() {
    override fun getContainerId(): Int = 0
    override fun getViewModel(): UserViewModel? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    lateinit var mNameEdt: EditText
    lateinit var mInfoTxt: TextView


    override fun initViewModelClass(): Class<UserViewModel>? {
        return UserViewModel::class.java
    }

    override fun getLayoutId() = R.layout.activity_user_info


    override fun onCreateLayout() {

        mNameEdt = findViewById(R.id.user_info_name_edt)
        mInfoTxt = findViewById(R.id.user_info_info_txt)
    }


    override fun observeViewModel() {
        user_info_get_info_btn.apply {
            setOnClickListener {
                mViewModel?.getUserByLoginInfo(mNameEdt.text.toString())
                    ?.observe(this@UserInfoActivity, object : BaseObserser<UserInfo>() {
                        override fun onSuccess(data: UserInfo) {
                            Log.d("Info1", "UserInfoActivity: " + data.name)
                            mInfoTxt.text = data.name
                        }

                        override fun onFail(error: String) {
                            Log.d("Info1", "UserInfoActivity: " + error)
                            mInfoTxt.text = error

                        }
                    })
            }
        }
    }

}



