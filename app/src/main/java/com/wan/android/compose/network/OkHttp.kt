package com.wan.android.compose.network

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * @Description:
 * @Date: 2024/9/3 14:36
 * @author:  liuwenjie09
 * @version: 1.0
 */
object OkHttp {

   private val client = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

   fun  getClient():OkHttpClient{
        return this.client
    }
}