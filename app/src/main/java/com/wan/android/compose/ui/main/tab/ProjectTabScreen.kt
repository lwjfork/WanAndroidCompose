package com.wan.android.compose.ui.main.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * @Description:
 * @Date: 2024/9/9 13:26
 * @author:  liuwenjie09
 * @version: 1.0
 */
@Composable
fun ProjectTabScreen() {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "ProjectTabScreen", color = Color.Red)

    }
}
