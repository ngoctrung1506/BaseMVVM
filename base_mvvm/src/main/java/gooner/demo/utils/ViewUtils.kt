package gooner.demo.utils

import android.content.res.Resources

fun pxToDp(px: Float): Float {
    val densityDpi: Float = Resources.getSystem().displayMetrics.densityDpi.toFloat()
    return px / (densityDpi / 160f)
}

fun dpToPx(dp: Float): Int {
    val density: Float = Resources.getSystem().displayMetrics.density
    return Math.round(dp * density)
}