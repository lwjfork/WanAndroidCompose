package com.wan.android.compose.component

import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * @Description:
 * @Date: 2024/9/9 09:48
 * @author:  liuwenjie09
 * @version: 1.0
 */
@Composable
fun CircularProgress(){
    CircularProgressIndicator(
        modifier = Modifier.width(56.dp),
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}