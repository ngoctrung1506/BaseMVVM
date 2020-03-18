package base.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import butterknife.ButterKnife
import butterknife.Unbinder
import tgo.lostandfound.R

abstract class BaseActivity<VM> : AppCompatActivity() where VM : BaseViewModel {

    var mUnbinder: Unbinder? = null
    var mViewModel: VM? = null

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

    abstract protected fun initViewModelClass(): Class<VM>?

    abstract protected fun onCreateLayout()

    abstract protected fun getLayoutId(): Int

    fun addView(layoutId: Int, fragment: BaseFragment<*>) {
        supportFragmentManager.beginTransaction().run {
            setCustomAnimations(R.anim.slide_in_right, R.anim.fade_in)
            add(layoutId, fragment).addToBackStack(null).commit()

        }
    }

    fun addView(layoutId: Int, fragment: BaseFragment<*>, fragmentName: String) {
        supportFragmentManager.beginTransaction().run {
            setCustomAnimations(R.anim.slide_in_right, R.anim.fade_in)
            add(layoutId, fragment).addToBackStack(fragmentName).commit()

        }
    }

    fun replaceView(layoutId: Int, fragment: BaseFragment<*>) {
        supportFragmentManager.beginTransaction().run {
            setCustomAnimations(R.anim.slide_in_right, R.anim.fade_in)
            replace(layoutId, fragment).addToBackStack(null).commit()
        }
    }

    fun replaceView(layoutId: Int, fragment: BaseFragment<*>, fragmentName: String) {
        supportFragmentManager.beginTransaction().run {
            setCustomAnimations(R.anim.slide_in_right, R.anim.fade_in)
            replace(layoutId, fragment).addToBackStack(fragmentName).commit()
        }
    }

    fun removeView(fragment: BaseFragment<*>) {
        supportFragmentManager.beginTransaction().run {
            setCustomAnimations(R.anim.slide_in_left, R.anim.fade_out)
            remove(fragment).commit()
        }
    }

    // TODO: implement this function when loading data is started
    fun showLoading() {
    }

    // TODO: implement this function when loading data is done
    fun hideLoading() {
    }


}