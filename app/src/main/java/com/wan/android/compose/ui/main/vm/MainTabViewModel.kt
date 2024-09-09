package com.wan.android.compose.ui.main.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wan.android.compose.model.MainBannerItem
import com.wan.android.compose.network.LoadingIdle
import com.wan.android.compose.network.LoadingStatus

/**
 * @Description:
 * @Date: 2024/9/9 15:28
 * @author:  liuwenjie09
 * @version: 1.0
 */
class MainTabViewModel : ViewModel() {
    private val _initLoading: MutableLiveData<LoadingStatus> = MutableLiveData(LoadingIdle)
    private val _bannerItems: MutableLiveData<List<MainBannerItem>> =
        MutableLiveData(mutableListOf())

    val initLoading: LiveData<LoadingStatus> = _initLoading
    val bannerItems: LiveData<List<MainBannerItem>> = _bannerItems

    fun updateInitLoading(isLoading: LoadingStatus) {
        this._initLoading.value = isLoading
    }

    fun updateBannerItems(bannerItems: List<MainBannerItem>?) {
        this._bannerItems.value = bannerItems
    }
}