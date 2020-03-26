package gooner.demo.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window

abstract class BaseDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window!!.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(isCancelable)
        if (getLayout() != 0) {
            val view = View.inflate(context, getLayout(), null)
            setContentView(view)
            initView(view)
        }
    }

    protected abstract val isCancelable: Boolean

    protected abstract fun getLayout(): Int

    protected abstract fun initView(view: View)
}