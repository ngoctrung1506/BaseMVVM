package tgo.lostandfound

import android.Manifest
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import tgo.lostandfound.adapter.PostAdapter
import tgo.lostandfound.model.Item
import tgo.lostandfound.model.Post
import android.content.Intent
import android.util.Log
import android.widget.*
import base.BaseActivity
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.frosquivel.magicalcamera.MagicalPermissions
import com.frosquivel.magicalcamera.MagicalCamera
import tgo.lostandfound.screen.main.MainScreenFragment


class MainActivity : BaseActivity() {

    @BindView(R.id.container_frame)
    lateinit var mContainerFr:FrameLayout


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
