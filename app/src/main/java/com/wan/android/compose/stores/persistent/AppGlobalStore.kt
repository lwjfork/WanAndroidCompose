package com.wan.android.compose.stores.persistent

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.wan.android.compose.stores.persistent.App.APPGlobalConfig
import java.io.InputStream
import java.io.OutputStream


object AppGlobalStoreSerializer : Serializer<APPGlobalConfig> {
    override val defaultValue: APPGlobalConfig
        get() = APPGlobalConfig.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): APPGlobalConfig {
        return APPGlobalConfig.parseFrom(input)
    }

    override suspend fun writeTo(t: APPGlobalConfig, output: OutputStream) {
        t.writeTo(output)
    }
}


val Context.APPGlobalConfigStore: DataStore<APPGlobalConfig> by dataStore(
    fileName = "app.pb",
    serializer = AppGlobalStoreSerializer
)

object AppGlobalStore {


}