package com.wan.android.compose.component

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.Window
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.DialogWindowProvider
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

/**
 * @Description:
 * @Date: 2024/9/8 14:19
 * @author:  liuwenjie09
 * @version: 1.0
 */
private tailrec fun Context.findWindow(): Window? =
    when (this) {
        is Activity -> window
        is ContextWrapper -> baseContext.findWindow()
        else -> null
    }

@Composable
fun rememberWindowInsetController(
    window: Window? = findWindow(),
): WindowInsetsControllerCompat? {
    val view = LocalView.current
    return remember(view, window) {
        window?.let {
            WindowCompat.getInsetsController(it, view)
        }
    }
}

@Composable
fun findWindow(): Window? =
    (LocalView.current.parent as? DialogWindowProvider)?.window
        ?: LocalView.current.context.findWindow()