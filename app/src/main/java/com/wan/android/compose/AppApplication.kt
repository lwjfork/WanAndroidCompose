package com.wan.android.compose

import android.app.Application
import com.wan.android.compose.stores.memory.GlobalContext

/**
 * @Description:
 * @Date: 2024/9/12 10:19
 * @author:  liuwenjie09
 * @version: 1.0
 */
class AppApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        GlobalContext.install(this)
    }
}