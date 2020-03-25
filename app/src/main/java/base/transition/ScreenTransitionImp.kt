package base.transition

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import base.mvvm.BaseFragment

class ScreenTransitionImp(
    private var mFragmentManager: FragmentManager,
    private var mContainerId: Int
) : ScreenTransitionIf {

    override fun getLastFragmentByTag(): String? {
        if (mFragmentManager.backStackEntryCount <= 0) return null

        val backEntry: FragmentManager.BackStackEntry

        (mFragmentManager.backStackEntryCount - 1).let {
            backEntry =
                if (it > 0) {
                    mFragmentManager.getBackStackEntryAt(it - 1)
                } else mFragmentManager.getBackStackEntryAt(it)
        }
        return backEntry.name
    }


    override fun loadScreen(tag: String) {

        if (mFragmentManager.findFragmentByTag(tag) != null) {

            mFragmentManager.run {
                findFragmentByTag(tag)?.let { currentFragment ->
                    if (currentFragment.isHidden) {
                        mFragmentManager.beginTransaction().run {
                            fragments.forEach {
                                hide(it)
                            }
                            show(currentFragment).commit()
                        }

                    }
                }
            }
        }
        // Xu ly them cac truong hop
    }

    override fun hideScreen(tag: String) {
        mFragmentManager.findFragmentByTag(tag)?.let {
            mFragmentManager.beginTransaction().hide(it).commit()
        }
    }

    override fun hideScreen(fragment: BaseFragment<*>) {
        mFragmentManager.beginTransaction().hide(fragment).commit()
    }


    override fun addScreen(fragment: BaseFragment<*>) {
        fragment.javaClass.name.let { name ->
            mFragmentManager.beginTransaction().run {
                replace(mContainerId, fragment, name)
                addToBackStack(name)
                commit()
            }
        }
    }

    override fun addScreen(fragment: BaseFragment<*>, bundle: Bundle?) {
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
        if (mFragmentManager.backStackEntryCount > 0)
            mFragmentManager.popBackStack()
    }

    override fun backScreen(tag: String, type: Int) {
        mFragmentManager.popBackStack(tag, type)
    }

}