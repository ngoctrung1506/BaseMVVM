package gooner.demo.mvvm

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import butterknife.ButterKnife
import butterknife.Unbinder
import gooner.demo.transition.ScreenTransitionImp
import gooner.demo.view.LoadingDialog


abstract class BaseActivity<VM> : AppCompatActivity() where VM : BaseViewModel {

    var mUnbinder: Unbinder? = null
    var mViewModel: VM? = null

    lateinit var mScreenTransitionImp: ScreenTransitionImp

    lateinit var mLoadingDialog: LoadingDialog

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

    protected abstract fun initViewModelClass(): Class<VM>?

    protected abstract fun onCreateLayout()

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    @IdRes
    protected abstract fun getContainerId(): Int


    fun showLoading() {
        if (!::mLoadingDialog.isInitialized) {
            mLoadingDialog = LoadingDialog(this)
            if (!mLoadingDialog.isShowing)
                mLoadingDialog.show()
        }
    }

    fun hideLoading() {
        if (mLoadingDialog.isShowing)
            mLoadingDialog.cancel()
    }


}