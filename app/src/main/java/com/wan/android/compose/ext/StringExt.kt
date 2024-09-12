package com.wan.android.compose.ext

/**
 * @Description:
 * @Date: 2024/9/12 10:18
 * @author:  liuwenjie09
 * @version: 1.0
 */
fun String.toCamelCase(): String {
    val result = StringBuilder()
    var capitalizeNext = false
    for (char in this) {
        if (char == '_' || char == '-') {
            capitalizeNext = true
        } else if (capitalizeNext) {
            result.append(char.toUpperCase())
            capitalizeNext = false
        } else {
            result.append(char)
        }
    }
    return result.toString()
}