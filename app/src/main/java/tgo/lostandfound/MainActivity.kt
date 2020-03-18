package tgo.lostandfound

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import base.di.module.ApiModule.Companion.BASE_URL_1
import base.mvvm.BaseActivity
import base.util.FilePickHandler
import butterknife.BindView
import io.reactivex.disposables.Disposable
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import tgo.lostandfound.api.file.IFileUpload
import tgo.lostandfound.model.User
import tgo.lostandfound.screen.user.UserViewModel
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class MainActivity : BaseActivity<UserViewModel>() {

    val PICK_IMAGE_REQUEST = 1
    val READ_EXTERNAL_REQUEST = 2

    override fun initViewModelClass(): Class<UserViewModel>? {
        return null
    }

    @Inject
    lateinit var mUser: User

    @BindView(R.id.container_frame)
    lateinit var mContainerFr: FrameLayout

    lateinit var mDisposable: Disposable

    lateinit var retrofit: Retrofit
    lateinit var service: IFileUpload

    override fun getLayoutId(): Int {
        return R.layout.activity_container
    }

    override fun onCreateLayout() {
//        DaggerDemoComponent.builder().build().inject(this)
//        Log.d("User1", mUser.toString())
//        replaceView(R.id.container_frame, MainScreenFragment())


        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Log.d(
                "Result",
                message
            )
        })
        interceptor.level =
            HttpLoggingInterceptor.Level.BODY

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_1)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder().apply {
                addInterceptor(interceptor)
                connectTimeout(60, TimeUnit.SECONDS); // connect timeout
                readTimeout(60, TimeUnit.SECONDS);
                retryOnConnectionFailure(true)
                addInterceptor(Interceptor {
                    val initialRequest = it.request()
                    val urlRequest = initialRequest.url().uri().toString()
//            var request = if (urlRequest.startsWith("${ApiModule.BASE_URL}")) {
                    var request = initialRequest.newBuilder()
                        .addHeader("Connection", "keep-alive")
                        .build()
                    /* }*/ //*else {
                    /*  initialRequest.newBuilder()
                          .build()
          }*/

                    val response = it.proceed(request)
                    /*
                            when (response.code()) {
                                401 -> {

                                }

                                400 -> {

                                }

                                403 -> {
                                }
                            }*/

                    return@Interceptor response
                })
            }.build()).build()

        service = retrofit.create(IFileUpload::class.java)

        requestPermionAndPickImage()

    }

    private fun requestPermionAndPickImage() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            pickImage()
            return
        }
        // Các bạn nhớ request permison cho các máy M trở lên nhé, k là crash ngay đấy.


        val result = ContextCompat.checkSelfPermission(
            this, READ_EXTERNAL_STORAGE
        )
        if (result == PackageManager.PERMISSION_GRANTED) {
            pickImage()
        } else {
            requestPermissions(
                arrayOf<String?>(
                    READ_EXTERNAL_STORAGE
                ), READ_EXTERNAL_REQUEST
            )
        }
    }

    fun pickImage() {
        // Gọi intent của hệ thống để chọn ảnh nhé.

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Select a File to Upload"),
            PICK_IMAGE_REQUEST
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode != READ_EXTERNAL_REQUEST) return
        if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pickImage()
        } else {
            Toast.makeText(
                applicationContext, "Fail",
                Toast.LENGTH_LONG
            ).show()
        }
    }


    override fun onActivityResult(
        requestCode: Int, resultCode: Int, data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            // Khi đã chọn xong ảnh thì chúng ta tiến hành upload thôi

            data?.let {
                val uri: Uri = data.data
                uploadFiles(uri)
            }

        }
    }


    fun uploadFiles(uri: Uri) {
        if (uri == null) return


        val file = File(FilePickHandler.getPath(this, uri)).also { file ->
            Log.d("Result", file.path)
        }

        val inputStream = FileInputStream(file)
        val buf: ByteArray
        buf = ByteArray(inputStream.available())
        while (inputStream.read(buf) !== -1);

        val requestBody: RequestBody = RequestBody.create(
            MediaType.parse("image/*"), buf
        )

        Log.d("Result", buf.size.toString())


//        val filePart: MultipartBody.Part? =
//            MultipartBody.Part.createFormData("file", file.getName(), requestBody)
//        Log.d("Result", file.getName())

        inputStream.close()


        service.upload(requestBody).enqueue(object : Callback<ResponseBody?> {

            override fun onFailure(call: retrofit2.Call<ResponseBody?>, t: Throwable) {
                Log.d("Result ", "onFailure " + t.message)

            }

            override fun onResponse(
                call: retrofit2.Call<ResponseBody?>,
                response: retrofit2.Response<ResponseBody?>
            ) {
                if (response == null || response.body() == null) {
                    Log.d("Result ", "onResponse Ok")
                    return
                }
                try {
                    val responseUrl: String? = response?.body()?.string()
                    Log.d("Result ", "onResponse" + responseUrl)

                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

        })

    }


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        Log.d("Path", "in Activity")
//    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposable.dispose()
        Log.d("User1", "onDestroy")

    }


}
