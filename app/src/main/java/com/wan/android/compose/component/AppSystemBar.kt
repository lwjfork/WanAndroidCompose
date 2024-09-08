package com.wan.android.compose.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.wan.android.compose.theme.BottomTabIconSize
import com.wan.android.compose.theme.BottomTabLabelSize
import com.wan.android.compose.theme.appColors

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
        it.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        it.hide(WindowInsetsCompat.Type.navigationBars())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppCenterAlignedTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
) {
    val titleComposable = @Composable {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentSize(),
            contentAlignment = Alignment.Center
        ) {
            title()
        }
    }
    val navigationIconComposable = @Composable {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            navigationIcon()
        }
    }
    val actionsComposable: @Composable RowScope.() -> Unit = @Composable {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            actions()
        }
    }
//    windowInsets = TopAppBarDefaults.windowInsets.exclude(WindowInsets.Companion.systemBars),
    CenterAlignedTopAppBar(
        title = titleComposable,
        navigationIcon = navigationIconComposable,
        actions = actionsComposable,
        colors = colors,
        modifier = modifier.height(64.dp)
    )
}

@Composable
fun AppTopBarIconContainer(painter: Painter, onClick: () -> Unit = {}) {
    Box(modifier = Modifier
        .height(40.dp)
        .width(40.dp)
        .wrapContentSize()
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(
                bounded = false,
                radius = 25.dp,
                color = MaterialTheme.colorScheme.primary
            )
        ) {
            onClick()
        }) {
        Icon(
            painter = painter, contentDescription = ""
        )
    }
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
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                            .clickable(
                                indication = rememberRipple(
                                    bounded = false,
                                    radius = 35.dp,
                                    color = MaterialTheme.colorScheme.primary
                                ), interactionSource = remember {
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
                                },
                                painter = painterResource(id = tab.get(index).second),
                                contentDescription = "Localized description",
                                tint = if (index == selectedIndex) {
                                    selectedIconColor
                                } else {
                                    unSelectedIconColor
                                },
                            )
                            Text(
                                text = tab.get(index).first, color = if (index == selectedIndex) {
                                    selectLabelColor
                                } else {
                                    unSelectedLabelColor
                                }, fontSize = if (index == selectedIndex) {
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