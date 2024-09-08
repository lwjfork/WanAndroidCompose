package com.wan.android.compose.component

import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @Description:
 * @Date: 2024/9/8 17:12
 * @author:  liuwenjie09
 * @version: 1.0
 */

@Composable
fun Modifier.rippleClickable(
    enabled: Boolean = true,
    bounded: Boolean = false,
    radius: Dp = 25.dp,
    color: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit
): Modifier {
    return this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = rippleStyle(bounded, radius, color),
        enabled,
        onClick = onClick
    )
}

@Composable
fun rippleStyle(
    bounded: Boolean = false,
    radius: Dp = 25.dp,
    color: Color = MaterialTheme.colorScheme.primary
): Indication {
    return rememberRipple(
        bounded,
        radius,
        color
    )
}

