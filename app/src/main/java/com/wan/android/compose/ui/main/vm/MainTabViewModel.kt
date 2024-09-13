package com.wan.android.compose.ui.main.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wan.android.compose.model.ArticleListItem
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
    private val _loadingStatus: MutableLiveData<LoadingStatus> = MutableLiveData(LoadingIdle)

    private val _bannerItems: MutableLiveData<List<MainBannerItem>> =
        MutableLiveData(mutableListOf())

    private val _dateItems: MutableLiveData<List<ArticleListItem>> =
        MutableLiveData(mutableListOf())

    val loadingStatus: LiveData<LoadingStatus> = _loadingStatus
    val bannerItems: LiveData<List<MainBannerItem>> = _bannerItems
    val dateItems: LiveData<List<ArticleListItem>> = _dateItems

    fun updateLoadingStatus(loadingStatus: LoadingStatus) {
        this._loadingStatus.value = loadingStatus
    }

    fun updateBannerItems(bannerItems: List<MainBannerItem>?) {
        this._bannerItems.value = bannerItems
    }

    fun updateDataItems(dateItems: List<ArticleListItem>?) {
        this._dateItems.value = dateItems
    }
}