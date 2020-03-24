package base.mvvm

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import base.transition.ScreenTransitionImp
import butterknife.ButterKnife
import butterknife.Unbinder


abstract class BaseActivity<VM> : AppCompatActivity() where VM : BaseViewModel {

    var mUnbinder: Unbinder? = null
    var mViewModel: VM? = null

    lateinit var mScreenTransitionImp: ScreenTransitionImp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mUnbinder = ButterKnife.bind(this)
        mScreenTransitionImp = ScreenTransitionImp(supportFragmentManager, getContainerId())

        val viewModelClass = initViewModelClass()
        if (viewModelClass != null) {
            // Viewmodel won't be destroyed when activity is destroy() and recreate()
            // get same instance
            this.mViewModel = if (mViewModel == null) {
                ViewModelProvider.AndroidViewModelFactory(application)
                    .create(viewModelClass)
            } else mViewModel
        }

        onCreateLayout()
        observeViewModel()
    }

    /**
     * Write code for Observe to update view when data is changed
     *
     */
    @Nullable
    protected abstract fun observeViewModel()


    override fun onDestroy() {
        super.onDestroy()
        mUnbinder?.unbind()
    }

    /**
     *
     * @return view model instance
     */
    protected abstract fun getViewModel(): VM?

    abstract protected fun initViewModelClass(): Class<VM>?

    abstract protected fun onCreateLayout()

    @LayoutRes
    abstract protected fun getLayoutId(): Int

    @IdRes
    abstract protected fun getContainerId(): Int

//    fun addView(layoutId: Int, fragment: BaseFragment<*>) {
//        supportFragmentManager.beginTransaction().run {
//            setCustomAnimations(R.anim.slide_in_right, R.anim.fade_in)
//            add(layoutId, fragment).addToBackStack(null).commit()
//
//        }
//    }
//
//    fun addView(layoutId: Int, fragment: BaseFragment<*>, fragmentName: String) {
//        supportFragmentManager.beginTransaction().run {
//            setCustomAnimations(R.anim.slide_in_right, R.anim.fade_in)
//            add(layoutId, fragment).addToBackStack(fragmentName).commit()
//
//        }
//    }
//
//    fun replaceView(layoutId: Int, fragment: BaseFragment<*>) {
//        supportFragmentManager.beginTransaction().run {
//            setCustomAnimations(R.anim.slide_in_right, R.anim.fade_in)
//            replace(layoutId, fragment).addToBackStack(null).commit()
//        }
//    }
//
//    fun replaceView(layoutId: Int, fragment: BaseFragment<*>, fragmentName: String) {
//        supportFragmentManager.beginTransaction().run {
//            setCustomAnimations(R.anim.slide_in_right, R.anim.fade_in)
//            replace(layoutId, fragment).addToBackStack(fragmentName).commit()
//        }
//    }
//
//    fun removeView(fragment: BaseFragment<*>) {
//        supportFragmentManager.beginTransaction().run {
//            setCustomAnimations(R.anim.slide_in_left, R.anim.fade_out)
//            remove(fragment).commit()
//        }
//    }

    // TODO: implement this function when loading data is started
    fun showLoading() {
    }

    // TODO: implement this function when loading data is done
    fun hideLoading() {
    }


}