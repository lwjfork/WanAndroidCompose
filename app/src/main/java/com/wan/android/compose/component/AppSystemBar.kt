package com.wan.android.compose.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.wan.android.compose.ui.theme.BottomTabIconSize
import com.wan.android.compose.ui.theme.BottomTabLabelSize
import com.wan.android.compose.ui.theme.appColors

/**
 * @Description:
 * @Date: 2024/9/3 17:36
 * @author:  liuwenjie09
 * @version: 1.0
 */
@Composable
fun SystemStatusBar(statusColor: Color? = null, isLight: Boolean? = null) {
    findWindow()?.statusBarColor =
        statusColor?.toArgb() ?: MaterialTheme.colorScheme.surface.toArgb()
    rememberWindowInsetController()?.let {
        it.isAppearanceLightStatusBars = isLight ?: MaterialTheme.appColors.isLight
        it.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

@Composable
fun SystemNavigationBar() {
    rememberWindowInsetController()?.let {
        it.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        it.hide(WindowInsetsCompat.Type.navigationBars())
    }
}

@Composable
fun AppTopBar() {

}

@Composable
fun AppNavigationBar(
    tab: List<Pair<String, Int>>,
    selectedIndex: Int,
    onSelectTab: (index: Int) -> Unit,
    selectedIconColor: Color = MaterialTheme.colorScheme.primary,
    unSelectedIconColor: Color = MaterialTheme.colorScheme.onBackground,
    selectLabelColor: Color = MaterialTheme.colorScheme.primary,
    unSelectedLabelColor: Color = MaterialTheme.colorScheme.onBackground,
    unSelectedIconSize: Dp = MaterialTheme.BottomTabIconSize,
    selectedIconSize: Dp = MaterialTheme.BottomTabIconSize,
    unSelectedTextSize: TextUnit = MaterialTheme.BottomTabLabelSize,
    selectedTextSize: TextUnit = MaterialTheme.BottomTabLabelSize
) {
    val selectTab = rememberUpdatedState(onSelectTab)
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        contentPadding = PaddingValues(0.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainer
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            HorizontalDivider(thickness = 0.5.dp)
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                for (index in tab.indices) {
                    key(index) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                                .align(Alignment.CenterVertically)
                                .clickable(indication = null, interactionSource = remember {
                                    MutableInteractionSource()
                                }) {
                                    selectTab.value(index)
                                },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                modifier = if (index == selectedIndex) {
                                    Modifier.size(selectedIconSize)
                                } else {
                                    Modifier.size(unSelectedIconSize)
                                } ,
                                painter = painterResource(id = tab.get(index).second),
                                contentDescription = "Localized description",
                                tint = if (index == selectedIndex) {
                                    selectedIconColor
                                } else {
                                    unSelectedIconColor
                                },
                            )
                            Text(
                                text = tab.get(index).first,
                                color = if (index == selectedIndex) {
                                    selectLabelColor
                                } else {
                                    unSelectedLabelColor
                                },
                                fontSize = if (index == selectedIndex) {
                                    selectedTextSize
                                } else {
                                    unSelectedTextSize
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}