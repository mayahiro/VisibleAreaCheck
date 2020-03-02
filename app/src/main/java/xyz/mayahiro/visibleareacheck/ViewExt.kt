package xyz.mayahiro.visibleareacheck

import android.graphics.Rect
import android.view.View
import kotlin.math.round

fun View.visiblePercentage(): Int {
    val visibleRect = Rect()
    getLocalVisibleRect(visibleRect)

    return round(((visibleRect.right - visibleRect.left) * (visibleRect.bottom - visibleRect.top)).toFloat() / (width * height) * 100).toInt()
}
