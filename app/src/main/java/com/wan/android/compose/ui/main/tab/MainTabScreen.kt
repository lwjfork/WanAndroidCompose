package com.wan.android.compose.ui.main.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastMap
import com.wan.android.compose.component.CircularProgress
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @Description:
 * @Date: 2024/9/9 13:26
 * @author:  liuwenjie09
 * @version: 1.0
 */

@Composable
fun MainTabScreen() {
    val loading = rememberSaveable{ mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()
    val itemsList = (0..100).toList()
    val items = remember {
        itemsList
    }
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        LaunchedEffect(Unit) {
            if (loading.value) {
                coroutineScope.launch {
                    delay(3000)
                    loading.value = false
                }
            }
        }
        if (loading.value) {
            CircularProgress()
        } else {
            LazyColumn() {
                items(items) { Text("Item is $it") }
            }
        }

    }
}

