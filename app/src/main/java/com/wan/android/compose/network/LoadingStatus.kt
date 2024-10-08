package com.wan.android.compose.network

/**
 * @Description:
 * @Date: 2024/9/9 21:31
 * @author:  liuwenjie09
 * @version: 1.0
 */
sealed interface LoadingStatus


object LoadingIdle : LoadingStatus
object LoadingSuccess : LoadingStatus
class LoadingEmpty(val msg: String? = "暂无数据，看看其他吧") : LoadingStatus
class LoadingError(val code: Int, val msg: String?) : LoadingStatus