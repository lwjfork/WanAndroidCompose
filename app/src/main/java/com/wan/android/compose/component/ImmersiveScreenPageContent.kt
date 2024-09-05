package com.wan.android.compose.component

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.Window
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.DialogWindowProvider
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.wan.android.compose.ui.theme.AppTheme
import com.wan.android.compose.ui.theme.appColors

/**
 * @Description:
 * @Date: 2024/9/4 20:07
 * @author:  liuwenjie09
 * @version: 1.0
 */
@Composable
fun ImmersiveScreenPageContent(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    AppTheme {
        StatusBar()
        NavigationBar()
        Scaffold(
            modifier = modifier.fillMaxSize(),
            bottomBar = bottomBar,
            topBar = topBar
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                content()
            }

        }
    }

}
private tailrec fun Context.findWindow(): Window? =
    when (this) {
        is Activity -> window
        is ContextWrapper -> baseContext.findWindow()
        else -> null
    }

@Composable
fun StatusBar(){
    findWindow()?.statusBarColor = MaterialTheme.appColors.statusBarColor.toArgb()
    rememberWindowInsetController()?.let {
        it.isAppearanceLightStatusBars = !MaterialTheme.appColors.isDark
        it.systemBarsBehavior =  WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}
@Composable
fun NavigationBar(){
    rememberWindowInsetController()?.let {
        it.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        it.hide(WindowInsetsCompat.Type.navigationBars())
    }
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
private fun findWindow(): Window? =
    (LocalView.current.parent as? DialogWindowProvider)?.window
        ?: LocalView.current.context.findWindow()


