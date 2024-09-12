package com.wan.android.compose.ui.splash

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.wan.android.compose.ext.toColor
import com.wan.android.compose.stores.persistent.APPGlobalConfigStore
import com.wan.android.compose.theme.ThemeColors
import com.wan.android.compose.theme.readThemeFromAssets
import com.wan.android.compose.theme.switchAppTheme
import com.wan.android.compose.ui.main.MainActivity
import kotlinx.coroutines.launch

/**
 * @Description:
 * @Date: 2024/9/6 14:31
 * @author:  liuwenjie09
 * @version: 1.0
 */
class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycleScope.launch {
            readThemeFromAssets(this@SplashActivity)
            APPGlobalConfigStore.data.collect {
                if (!TextUtils.isEmpty(it.themeColor)) {
                    val color = it.themeColor.toColor()
                    if (ThemeColors.containsKey(color)) {
                        switchAppTheme(color)
                    }
                }
                val intent = Intent()
                intent.setClass(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        setContent {

        }
    }

}