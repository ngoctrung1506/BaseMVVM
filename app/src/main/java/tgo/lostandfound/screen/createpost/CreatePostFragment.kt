package tgo.lostandfound.screen.createpost

import android.Manifest
import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.room.Room
import base.BaseFragment
import butterknife.BindView
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.frosquivel.magicalcamera.MagicalCamera
import com.frosquivel.magicalcamera.MagicalPermissions

import tgo.lostandfound.R
import tgo.lostandfound.database.AppDataBase
import tgo.lostandfound.model.Item


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CreatePostFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CreatePostFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CreatePostFragment : BaseFragment() {

    @BindView(R.id.create_post_add_object_btn)
    lateinit var mAddObjectBtn: Button

    @BindView(R.id.create_post_save_btn)
    lateinit var mSaveBtn: Button

    @BindView(R.id.create_post_object_name_txt)
    lateinit var mNameObjectTxt: EditText

    @BindView(R.id.create_post_object_img)
    lateinit var mObjectImg: ImageView

    @BindView(R.id.create_post_add_place_btn)
    lateinit var mAddPlaceBtn: Button

    @BindView(R.id.create_post_place_name_txt)
    lateinit var mNamePlaceTxt: EditText

    @BindView(R.id.create_post_place_img)
    lateinit var mPlaceImg: ImageView

    @BindView(R.id.create_post_btn_back)
    lateinit var mBackBtn: ImageView

    private lateinit var magicalPermissions: MagicalPermissions
    private lateinit var magicalCamera: MagicalCamera

    private val RESIZE_PHOTO_PIXELS_PERCENTAGE = 80

    private val CREATE_POST_ADD_OBJECT = 1000
    private val CREATE_POST_ADD_PLACE = 1001

    lateinit var mDatabase: AppDataBase


    override fun getLayoutId(): Int {
        return R.layout.fragment_create_post
    }

    override fun onCreateLayout() {
        val permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        magicalPermissions = MagicalPermissions(this, permissions)
        magicalCamera = MagicalCamera(activity, RESIZE_PHOTO_PIXELS_PERCENTAGE, magicalPermissions)

        activity?.let {
            mDatabase = Room.databaseBuilder(
                activity!!.applicationContext,
                AppDataBase::class.java, "item").allowMainThreadQueries().build()

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        val map = magicalPermissions.permissionResult(requestCode, permissions, grantResults)
        for (permission in map.keys) {
            Log.d("PERMISSIONS", permission + " was: " + map[permission])
        }
    }

    @OnClick(R.id.create_post_add_object_btn)
    fun takeObjectPhoto() {
        magicalCamera.takeFragmentPhoto(this)
        startActivityForResult(magicalCamera.getIntentFragment(), CREATE_POST_ADD_OBJECT)
        Log.d("Path", "take object photo")
    }

    @OnClick(R.id.create_post_add_place_btn)
    fun takePlacePhoto() {
        magicalCamera.takeFragmentPhoto(this)
        startActivityForResult(magicalCamera.getIntentFragment(), CREATE_POST_ADD_PLACE)
        Log.d("Path", "take place photo")
    }

    @OnClick(R.id.create_post_btn_back)
    fun onBack() {
        backToScreen(this)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        magicalCamera.resultPhoto(requestCode, resultCode, data);

        //this is for rotate picture in this method
        magicalCamera.resultPhoto(requestCode, resultCode, data, MagicalCamera.ORIENTATION_ROTATE_90);
        var path = magicalCamera.savePhotoInMemoryDevice(
            magicalCamera.photo,
            "first_img",
            MagicalCamera.JPEG,
            true
        )

        if (path != null) {
            Log.d("Path", path)
            if (requestCode == CREATE_POST_ADD_OBJECT)
                Glide.with(this).load(path).into(mObjectImg)
            else Glide.with(this).load(path).into(mPlaceImg)
            mDatabase.itemDao().insertItem(Item(mNameObjectTxt.text.toString(), path, mNamePlaceTxt.text.toString(), "a", "Jonh"))
             Log.d("Item", mDatabase.itemDao().getAllItem().toString())
        } else {
            Log.d("Path", "null")
        }
    }


}
