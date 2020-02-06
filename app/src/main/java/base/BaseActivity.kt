package base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import butterknife.ButterKnife
import butterknife.Unbinder
import tgo.lostandfound.R

abstract class BaseActivity : AppCompatActivity() {

    var mUnbinder: Unbinder? = null
    var mViewModel: BaseViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mUnbinder = ButterKnife.bind(this)

        val viewModelClass = initViewModelClass()
        if (viewModelClass != null) {
            mViewModel = ViewModelProvider.AndroidViewModelFactory(application)
                .create(viewModelClass)
        }
        onCreateLayout()
    }

    override fun onDestroy() {
        super.onDestroy()
        mUnbinder?.unbind()
    }

    abstract protected fun initViewModelClass(): Class<out BaseViewModel>?

    abstract protected fun onCreateLayout()

    abstract protected fun getLayoutId(): Int

    fun addView(layoutId: Int, fragment: BaseFragment) {
        supportFragmentManager?.beginTransaction()?.run {
            setCustomAnimations(R.anim.slide_in_right, R.anim.fade_in)
            add(layoutId, fragment)?.addToBackStack(null).commit()

        }
    }

    fun addView(layoutId: Int, fragment: BaseFragment, fragmentName: String) {
        supportFragmentManager?.beginTransaction()?.run {
            setCustomAnimations(R.anim.slide_in_right, R.anim.fade_in)
            add(layoutId, fragment)?.addToBackStack(fragmentName).commit()

        }
    }

    fun replaceView(layoutId: Int, fragment: BaseFragment) {
        supportFragmentManager?.beginTransaction()?.run {
            setCustomAnimations(R.anim.slide_in_right, R.anim.fade_in)
            replace(layoutId, fragment)?.addToBackStack(null).commit()
        }
    }

    fun replaceView(layoutId: Int, fragment: BaseFragment, fragmentName: String) {
        supportFragmentManager?.beginTransaction()?.run {
            setCustomAnimations(R.anim.slide_in_right, R.anim.fade_in)
            replace(layoutId, fragment)?.addToBackStack(fragmentName).commit()
        }
    }

    fun removeView(fragment: BaseFragment) {
        supportFragmentManager?.beginTransaction()?.run {
            setCustomAnimations(R.anim.slide_in_left, R.anim.fade_out)
            remove(fragment)?.commit()
        }
    }


}