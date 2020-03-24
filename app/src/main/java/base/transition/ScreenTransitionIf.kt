package base.transition

import android.os.Bundle
import base.mvvm.BaseFragment

interface ScreenTransitionIf {

    /**
     * Transit to another screen
     *
     * @baseFragment an instance of fragment
     *
     */
    fun transitionTo(fragment: BaseFragment<*>)


    /**
     * Transit to another screen with bundle
     *
     * @baseFragment an instance of fragment
     *
     */
    fun transitionTo(fragment: BaseFragment<*>, bundle: Bundle?)

    /**
     * Back to previous screen
     *
     */
    fun backScreen()


}

