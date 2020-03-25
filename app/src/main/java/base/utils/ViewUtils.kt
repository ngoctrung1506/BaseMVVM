package base.utils

import android.content.res.Resources

fun pxToDp(px: Float): Float {
    val densityDpi: Float = Resources.getSystem().getDisplayMetrics().densityDpi.toFloat()
    return px / (densityDpi / 160f)
}

fun dpToPx(dp: Float): Int {
    val density: Float = Resources.getSystem().getDisplayMetrics().density
    return Math.round(dp * density)
}