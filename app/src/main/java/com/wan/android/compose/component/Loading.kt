package com.wan.android.compose.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wan.android.compose.R
import com.wan.android.compose.network.LoadingEmpty
import com.wan.android.compose.network.LoadingError
import com.wan.android.compose.network.LoadingIdle
import com.wan.android.compose.network.LoadingStatus
import com.wan.android.compose.network.LoadingSuccess

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
fun LoadingErrorView(clickText: String? = "网络错误请重试", onRetryClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 5.dp, vertical = 5.dp)
                .clickable(
                    onClick = onRetryClick,
                    indication = null,
                    interactionSource = null
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_net_error),
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp),
                contentDescription = "错误图"
            )
            Text(
                text = clickText ?: "网络错误请重试",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 5.dp)
            )
        }
    }

}

@Composable
fun LoadingEmptyView(clickText: String? = "暂无数据看看其他吧") {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 5.dp, vertical = 5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_data_empty),
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp),
                contentDescription = "暂无数据"
            )
            Text(
                text = clickText ?: "暂无数据看看其他吧",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 5.dp)
            )
        }
    }
}

@Composable
fun LoadingStatusView(loadingStatus: LoadingStatus = LoadingIdle, onRetryClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        when (loadingStatus) {
            is LoadingIdle -> CircularProgress()
            is LoadingSuccess -> {}
            is LoadingError -> LoadingErrorView(loadingStatus.msg, onRetryClick)
            is LoadingEmpty -> LoadingEmptyView(loadingStatus.msg)
        }
    }
}