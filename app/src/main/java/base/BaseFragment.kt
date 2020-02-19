package base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import butterknife.ButterKnife
import butterknife.Unbinder
import tgo.lostandfound.R


abstract class BaseFragment<VM> : Fragment() where VM : BaseViewModel {

    var mContext: BaseActivity<*>? = null

    var mUnbinder: Unbinder? = null
    var mViewModel: VM? = null

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
            mContext?.let {
                mViewModel = ViewModelProvider.AndroidViewModelFactory(it.application)
                    .create(viewModelClass)
            }
        }
        onCreateLayout()
    }

    abstract protected fun onCreateLayout()

    abstract protected fun getViewModelClass(): Class<VM>?

    abstract fun getLayoutId(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            mContext = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        mContext = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mUnbinder?.unbind()
    }

    /**
     *  Change to new screen.
     *
     *  @newScreen fragment of new screen.
     *
     */
    fun changeToScreen(newScreen: BaseFragment<VM>) {
        mContext?.replaceView(R.id.container_frame, newScreen)
        Log.d("AAA", "change screen")
    }

    /**
     * Back to previous screen.
     *
     * @currentScreen fragment of current screen.
     *
     */
    fun backToPreviousScreen(currentScreen: BaseFragment<VM>) {
        mContext?.removeView(currentScreen)
    }

//    // TODO: implement this function when loading data is started
//    fun showLoading() {
//    }
//
//    // TODO: implement this function when loading data is done
//    fun hideLoading() {
//    }
}