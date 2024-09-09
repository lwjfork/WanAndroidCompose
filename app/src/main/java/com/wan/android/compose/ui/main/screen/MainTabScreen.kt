package com.wan.android.compose.ui.main.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.wan.android.compose.component.CircularProgress
import com.wan.android.compose.model.MainBannerItem
import com.wan.android.compose.network.LoadingIdle
import com.wan.android.compose.network.LoadingSuccess
import com.wan.android.compose.network.MainService
import com.wan.android.compose.ui.main.vm.MainTabViewModel
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
    val mainTabViewModel = viewModel(modelClass = MainTabViewModel::class.java)
    val loadingStatus = mainTabViewModel.initLoading.observeAsState()
    val bannerItems = mainTabViewModel.bannerItems.observeAsState()
    val coroutineScope = rememberCoroutineScope()
    val itemsList = (0..100).toList()
    val items = remember {
        itemsList
    }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val mainService = MainService.getInstance()
            val bannerItems = mainService.getMainBanner().data
            if (!bannerItems.isNullOrEmpty()) {
                mainTabViewModel.updateBannerItems(bannerItems)
            }
        }
    }
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        LaunchedEffect(Unit) {
            if (loadingStatus.value is LoadingIdle) {
                coroutineScope.launch {
                    delay(3000)
                    mainTabViewModel.updateInitLoading(LoadingSuccess)
                }
            }
        }
        if (loadingStatus.value is LoadingIdle) {
            CircularProgress()
        } else if (loadingStatus.value is LoadingSuccess) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                if (!bannerItems.value.isNullOrEmpty()) {
                    item {
                        ImageBanner(bannerItems.value)
                    }
                }
                items(items) { Text("Item is $it") }
            }

        }

    }
}

@Composable
fun ImageBanner(bannerItems: List<MainBannerItem>?) {
    if (!bannerItems.isNullOrEmpty()) {
        val pagerState = rememberPagerState(initialPage = Int.MAX_VALUE.div(2)) {
            Int.MAX_VALUE
        }
        HorizontalPager(
            state = pagerState, key = {
                bannerItems[it.mod(bannerItems.size)].imagePath.orEmpty()
            }, modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.65f)
        ) {
            AsyncImage(
                model = bannerItems.get(it.mod(bannerItems.size)).imagePath!!,
                contentScale = ContentScale.Crop,
                contentDescription = "",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

