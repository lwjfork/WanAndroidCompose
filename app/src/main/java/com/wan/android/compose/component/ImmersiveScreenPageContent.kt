package com.wan.android.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.wan.android.compose.theme.AppTheme

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
    statusColor: Color? = null,
    isLight:Boolean? = null,
    content: @Composable () -> Unit = {},
) {
    AppTheme {
        SystemStatusBar(statusColor,isLight)
        SystemNavigationBar()
        Scaffold(
            modifier = modifier.fillMaxSize(),
            bottomBar = bottomBar,
            topBar = topBar
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(innerPadding),
            ) {
                content()
            }

        }
    }

}




