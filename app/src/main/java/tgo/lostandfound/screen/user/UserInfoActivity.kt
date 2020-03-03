package tgo.lostandfound.screen.user

import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import base.BaseActivity
import kotlinx.android.synthetic.main.activity_user_info.*
import tgo.lostandfound.R

class UserInfoActivity : BaseActivity<UserViewModel>() {


    lateinit var mNameEdt: EditText
    lateinit var mInfoTxt: TextView


    override fun initViewModelClass(): Class<UserViewModel>? {
        return UserViewModel::class.java
    }

    override fun getLayoutId() = R.layout.activity_user_info


    override fun onCreateLayout() {

        mNameEdt = findViewById(R.id.user_info_name_edt)
        mInfoTxt = findViewById(R.id.user_info_info_txt)

        user_info_get_info_btn.apply {
            setOnClickListener {
                mViewModel?.getUserByLoginInfo(mNameEdt.text.toString())
                    ?.observe(this@UserInfoActivity, Observer {
                        Log.d("Info", "Info: " + it.name)
                        mInfoTxt.text = it.name
                    })
            }
        }


//        mViewModel?.userInfo?.observe(this, Observer {
//            Log.d("Info", "UserInfo: " + it.name + " - " + it.bio)
//        })

    }


}
