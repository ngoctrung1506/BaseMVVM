package base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import tgo.lostandfound.R

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        ButterKnife.bind(this)
        onCreateLayout()
    }

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

    fun replaceView(layoutId: Int, fragment: BaseFragment, fragmentName:String) {
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