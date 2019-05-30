package base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import tgo.lostandfound.R

abstract class BaseFragment : Fragment() {

    lateinit var mContext:BaseActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view:View = inflater.inflate(getLayoutId(), container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view)
        onCreateLayout()
    }

    abstract protected fun onCreateLayout()

    abstract fun getLayoutId(): Int

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context != null && context is BaseActivity){
            mContext = context
        }
    }

    /**
     *  Change to new screen.
     *
     *  @newScreen fragment of new screen.
     *
     */
    fun changeToScreen(newScreen:BaseFragment){
        mContext.replaceView(R.id.container_frame, newScreen)
        Log.d("AAA", "change screen")
    }

    /**
     * Back to previous screen.
     *
     * @currentScreen fragment of current screen.
     *
     */
    fun backToPreviousScreen(currentScreen: BaseFragment){
        mContext.removeView(currentScreen)
    }
}