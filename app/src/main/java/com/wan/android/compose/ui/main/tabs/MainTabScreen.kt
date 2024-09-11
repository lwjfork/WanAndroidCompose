package com.wan.android.compose.ui.main.tabs

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.wan.android.compose.component.CircularProgress
import com.wan.android.compose.model.MainBannerItem
import com.wan.android.compose.network.LoadingIdle
import com.wan.android.compose.network.LoadingSuccess
import com.wan.android.compose.network.MainService
import com.wan.android.compose.theme.appColors
import com.wan.android.compose.theme.toColor
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
    val dateItems = mainTabViewModel.dateItems.observeAsState()
    val coroutineScope = rememberCoroutineScope()


    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val mainService = MainService.getInstance()
            val bannerItems = mainService.getMainBanner().data
            if (!bannerItems.isNullOrEmpty()) {
                mainTabViewModel.updateBannerItems(bannerItems)
            }
           val dataItems =  mainService.getArticleList(1).data?.datas
            if (!dataItems.isNullOrEmpty()) {
                mainTabViewModel.updateDataItems(dataItems)
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
                if(!dateItems.value.isNullOrEmpty()){
                    dateItems.value!!.forEach {
                        item(key = it) {
                          Text(text = it.title!!, color = Color.Red)
                        }
                    }

                }
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
        val selectedColor = MaterialTheme.appColors.colorPalette.primary90
        val unselectedColor = MaterialTheme.appColors.colorPalette.neutral100
        val currentIndex = pagerState.currentPage.mod(bannerItems.size)
        val bannerItem = bannerItems[currentIndex]
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.65f)
        ) {
            HorizontalPager(
                state = pagerState, key = {
                    bannerItems[it.mod(bannerItems.size)].imagePath.orEmpty()
                }, modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.65f)
            ) {
                AsyncImage(
                    model = bannerItems[it.mod(bannerItems.size)].imagePath!!,
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Row(
                modifier = Modifier
                    .height(35.dp)
                    .fillMaxWidth()
                    .background("#50D9D9D9".toColor())
                    .align(Alignment.BottomEnd),
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (!bannerItem.title.isNullOrEmpty()) {
                    Text(
                        text = bannerItem.title!!, color = Color.White, fontSize = 14.sp,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .weight(1f)
                        .height(30.dp)
                        .padding(end = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End)
                ) {
                    for (i in bannerItems.indices) {
                        key(i) {
                            Canvas(
                                modifier = Modifier
                                    .size(8.dp),
                                onDraw = {
                                    if (i == currentIndex) {
                                        drawCircle(selectedColor)
                                    } else {
                                        drawCircle(unselectedColor)
                                    }
                                }
                            )
                        }
                    }

                }
            }
        }
    }
}

