package com.wan.android.compose.ext

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner

/**
 * @Description:
 * @Date: 2024/9/9 15:39
 * @author:  liuwenjie09
 * @version: 1.0
 */

@Composable
fun <T : ViewModel> getViewModel(modelClass: Class<T>): T {
    return ViewModelProvider(LocalViewModelStoreOwner.current!!).get(modelClass)
}