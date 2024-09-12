package com.wan.android.compose.stores.memory

import android.app.Application

object GlobalContext {

    private lateinit var application: Application

    fun install(application: Application){
        this.application = application
    }

    fun getApplication():Application{
        return this.application
    }

}