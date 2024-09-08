package com.wan.android.compose.ui.activity.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * @Description:
 * @Date: 2024/9/8 19:45
 * @author:  liuwenjie09
 * @version: 1.0
 */

@Composable
fun MainTabScreen() {
    Text(
        text = "MainTabScreen",
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
        color = Color.White
    )
}