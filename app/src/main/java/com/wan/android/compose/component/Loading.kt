package com.wan.android.compose.component

import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @Description:
 * @Date: 2024/9/9 09:48
 * @author:  liuwenjie09
 * @version: 1.0
 */
@Composable
@Preview(showBackground = true)
fun CircularProgress(){
    CircularProgressIndicator(
        modifier = Modifier.width(45.dp),
        color = MaterialTheme.colorScheme.primary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}