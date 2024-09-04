package com.wan.android.compose.component

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.wan.android.compose.ui.theme.AppTheme

/**
 * @Description:
 * @Date: 2024/9/4 20:07
 * @author:  liuwenjie09
 * @version: 1.0
 */
@Composable
fun SafePageContent(
    modifier: Modifier = Modifier,
    statusBarColor: Color = Color.Transparent,
    statusBarIconDarkStyle:Boolean = true,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    val StatusBarColor = rememberUpdatedState(newValue = statusBarColor)
    val isDarkStyle = rememberUpdatedState(newValue = statusBarIconDarkStyle)
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = StatusBarColor.value.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDarkStyle.value
        }
    }
    AppTheme {
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