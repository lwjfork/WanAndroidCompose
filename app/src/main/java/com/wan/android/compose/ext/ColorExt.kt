package com.wan.android.compose.ext

import androidx.compose.ui.graphics.Color

/**
 * @Description:
 * @Date: 2024/9/12 10:04
 * @author:  liuwenjie09
 * @version: 1.0
 */
fun Color.toHexString(): String {
    return String.format(
        "#%02X%02X%02X%02X",
        (this.alpha * 255).toInt(),
        (this.red * 255).toInt(),
        (this.green * 255).toInt(),
        (this.blue * 255).toInt()
    )
}

fun String.toColor(): Color {
    return Color(android.graphics.Color.parseColor(this))
}
