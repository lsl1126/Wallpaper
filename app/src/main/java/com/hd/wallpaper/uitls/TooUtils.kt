package com.hd.wallpaper.uitls

import android.content.Context


fun getWidthPixels(context: Context): Int {
    return context.resources.displayMetrics.widthPixels
}

fun getHeightPixels(context: Context): Int {
    return context.resources.displayMetrics.heightPixels
}

fun dp2px(context: Context, dp: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

fun px2dp(context: Context, px: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (px / scale + 0.5f).toInt()
}
