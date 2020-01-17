package base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import butterknife.Unbinder
import tgo.lostandfound.R

abstract class BaseFragment : Fragment() {

    lateinit var mContext: BaseActivity
    var mUnbinder: Unbinder? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUnbinder = ButterKnife.bind(this, view)
        onCreateLayout()
    }

    abstract protected fun onCreateLayout()

    abstract fun getLayoutId(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context != null && context is BaseActivity) {
            mContext = context
        }
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
    fun changeToScreen(newScreen: BaseFragment) {
        mContext.replaceView(R.id.container_frame, newScreen)
        Log.d("AAA", "change screen")
    }

    /**
     * Back to previous screen.
     *
     * @currentScreen fragment of current screen.
     *
     */
    fun backToPreviousScreen(currentScreen: BaseFragment) {
        mContext.removeView(currentScreen)
    }
}