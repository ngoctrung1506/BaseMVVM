package tgo.lostandfound.screen.user

import android.content.Intent
import android.widget.EditText
import android.widget.TextView
import base.BaseActivity
import kotlinx.android.synthetic.main.activity_user_info.*
import tgo.lostandfound.MainActivity
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
                //                mViewModel?.getUserByLoginInfo(mNameEdt.text.toString())
//                    ?.observe(this@UserInfoActivity, object : BaseObserser<UserInfo>() {
//                        override fun onSuccess(data: UserInfo) {
//                            Log.d("Info", "UserInfoActivity: " + data.name)
//                            mInfoTxt.text = data.name
//                        }
//
//                        override fun onFail(error: String) {
//                            Log.d("Info", "UserInfoActivity: " + error)
//                            mInfoTxt.text = error
//
//                        }
//                    })
                Intent(this@UserInfoActivity, MainActivity::class.java).also { intent ->
                    startActivity(intent)
                }
                finish()
            }
        }

    }
}



