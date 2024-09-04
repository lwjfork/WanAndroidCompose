package com.wan.android.compose.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.wanandroid.com")
        .client(OkHttp.getClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun <T> createService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

}