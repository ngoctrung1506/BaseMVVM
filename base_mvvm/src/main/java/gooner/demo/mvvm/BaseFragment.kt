package gooner.demo.mvvm

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import gooner.demo.transition.ScreenTransitionImp
import butterknife.ButterKnife
import butterknife.Unbinder


abstract class BaseFragment<VM> : Fragment() where VM : BaseViewModel {

    var mActivity: BaseActivity<*>? = null

    var mUnbinder: Unbinder? = null
    var mViewModel: VM? = null
    lateinit var mScreenTransitionImp: ScreenTransitionImp

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUnbinder = ButterKnife.bind(this, view)
        val viewModelClass = getViewModelClass()
        if (viewModelClass != null) {
            mActivity?.let {
                // Viewmodel won't be destroyed when activity is destroy() and recreate()
                // get same instance
                this.mViewModel = if (this.mViewModel == null) {
                    ViewModelProvider.AndroidViewModelFactory(it.application)
                        .create(viewModelClass)
                } else this.mViewModel
            }
        }
        onCreateLayout()
    }

    abstract protected fun onCreateLayout()

    abstract protected fun getViewModelClass(): Class<VM>?

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            mActivity = context.also {
                this.mScreenTransitionImp = it.mScreenTransitionImp
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        mActivity = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mUnbinder?.unbind()
    }

}