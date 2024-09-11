package com.wan.android.compose.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wan.android.compose.network.LoadingError
import com.wan.android.compose.network.LoadingIdle
import com.wan.android.compose.network.LoadingStatus

/**
 * @Description:
 * @Date: 2024/9/9 09:48
 * @author:  liuwenjie09
 * @version: 1.0
 */
@Composable
fun CircularProgress() {
    CircularProgressIndicator(
        modifier = Modifier.width(45.dp),
        color = MaterialTheme.colorScheme.primary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}

@Composable
fun LoadingError() {
    Column {

        Text(text = "网络错误请重试")
    }
}

@Composable
fun LoadingStatusView(loadingStatus: LoadingStatus) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (loadingStatus == LoadingIdle) {
            CircularProgress()
        }
        if (loadingStatus is LoadingError) {
            LoadingError()
        }
    }
}