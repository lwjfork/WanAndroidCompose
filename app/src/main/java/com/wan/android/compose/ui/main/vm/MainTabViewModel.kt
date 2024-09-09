package com.wan.android.compose.ui.main.vm

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @Description:
 * @Date: 2024/9/9 15:28
 * @author:  liuwenjie09
 * @version: 1.0
 */
class MainTabViewModel : ViewModel() {

    private val _initLoading = MutableLiveData(true)

    val initLoading: LiveData<Boolean> = _initLoading
    var state: LazyListState? = null

    fun updateInitLoading(isLoading: Boolean) {
        this._initLoading.value = isLoading
    }

    fun initListState(state: LazyListState?) {
        this.state = state
    }

}