package base.transition

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import base.mvvm.BaseFragment

class ScreenTransitionImp(
    private var mFragmentManager: FragmentManager,
    private var mContainerId: Int
) : ScreenTransitionIf {


    override fun transitionTo(fragment: BaseFragment<*>) {
        fragment.javaClass.name.let { name ->
            mFragmentManager.beginTransaction().run {
                replace(mContainerId, fragment, name)
                addToBackStack(name)
                commit()
            }
        }

    }

    override fun transitionTo(fragment: BaseFragment<*>, bundle: Bundle?) {
        fragment.run {
            bundle?.let {
                fragment.arguments = bundle
            }
            javaClass.name.let { name ->
                mFragmentManager.beginTransaction().run {
                    replace(mContainerId, fragment, name)
                    addToBackStack(name)
                    commit()
                }
            }
        }
    }

    override fun backScreen() {
        mFragmentManager.popBackStack()
    }
}