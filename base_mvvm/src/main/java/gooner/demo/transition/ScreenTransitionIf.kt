package gooner.demo.transition

import android.os.Bundle
import gooner.demo.mvvm.BaseFragment

interface ScreenTransitionIf {

    /**
     * Transit to another screen
     *
     * @baseFragment an instance of fragment
     *
     */
    fun addScreen(fragment: BaseFragment<*>)


    /**
     * Transit to another screen with bundle
     *
     * @baseFragment an instance of fragment
     *
     */
    fun addScreen(fragment: BaseFragment<*>, bundle: Bundle?)

    /**
     * Back to previous screen
     *
     */
    fun backScreen()

    /**
     * Back to previous screen with specific tag
     *
     */
    fun backScreen(tag: String, type: Int)

    /**
     * Load existed screen.
     *
     * @param tag tag of existed screen
     *
     */
    fun loadScreen(tag: String)


    /**
     * Hide screen.
     *
     * @param tag tag of screen.
     *
     */
    fun hideScreen(tag: String)

    /**
     * Hide screen.
     *
     * @param fragment fragment to hide.
     *
     */
    fun hideScreen(fragment: BaseFragment<*>)

    /**
     * Get fragment on the top of back stack
     */
    fun getLastFragmentByTag(): String?


}

