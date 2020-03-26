package gooner.demo.view

import android.content.Context
import android.view.View
import gooner.demo.base_mvvm.R

class LoadingDialog(context: Context) : BaseDialog(context) {

    override val isCancelable: Boolean
        get() = false

    override fun getLayout() = R.layout.progress_dialog

    override fun initView(view: View) {
        setCanceledOnTouchOutside(false)
    }
}