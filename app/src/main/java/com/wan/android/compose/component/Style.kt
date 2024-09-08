package com.wan.android.compose.component

/**
 * @Description:
 * @Date: 2024/9/8 17:12
 * @author:  liuwenjie09
 * @version: 1.0
 */
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RippleClickableBox(onClick: () -> Unit) {
    Surface(
        shape = MaterialTheme.shapes.small,
        onClick = onClick,
        modifier = Modifier
            .size(100.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ){
                onClick()
            }
    ) {
        // 这里可以放置你的内容
    }
}