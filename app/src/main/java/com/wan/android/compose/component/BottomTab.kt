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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wan.android.compose.ui.theme.appColors

/**
 * @Description:
 * @Date: 2024/9/3 17:36
 * @author:  liuwenjie09
 * @version: 1.0
 */

@Composable
fun BottomTab(
    tab: List<Pair<String, Int>>,
    selectedIndex: Int,
    onSelectTab: (index: Int) -> Unit
) {
    val selected = rememberUpdatedState(newValue = selectedIndex)
    val selectTab = rememberUpdatedState(onSelectTab)
    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        contentPadding = PaddingValues(0.dp),
        containerColor = Color.White
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
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = tab.get(index).second),
                                contentDescription = "Localized description",
                                tint = if (index == selected.value) {
                                    MaterialTheme.appColors.bottomTabSelectedColor
                                } else {
                                    MaterialTheme.appColors.bottomTabDefaultColor
                                },
                            )
                            Text(
                                text = tab.get(index).first,
                                color = if (index == selected.value) {
                                    MaterialTheme.appColors.bottomTabSelectedColor
                                } else {
                                    MaterialTheme.appColors.bottomTabDefaultColor
                                },
                                fontSize = if (index == selected.value) {
                                    13.sp
                                } else {
                                    12.sp
                                },

                                )
                        }
                    }
                }
            }
        }
    }
}