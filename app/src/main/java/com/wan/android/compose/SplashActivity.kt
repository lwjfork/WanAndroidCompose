package com.wan.android.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.wan.android.compose.theme.readThemeFromAssets
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
            val intent = Intent()
            intent.setClass(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        setContent {

        }
    }

}