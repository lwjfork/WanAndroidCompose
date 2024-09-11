package com.wan.android.compose.ui.activity.change_app_theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.wan.android.compose.component.ImmersiveScreenPageContent
import com.wan.android.compose.stores.persistent.APPGlobalConfigStore
import com.wan.android.compose.theme.ThemeColors
import com.wan.android.compose.theme.switchAppTheme
import kotlinx.coroutines.launch

class ChangeAppThemeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val coroutineScope = rememberCoroutineScope()
            ImmersiveScreenPageContent {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    for (themeColor in ThemeColors) {
                        Text(
                            text = "选择该颜色", modifier = Modifier
                                .background(themeColor.key)
                                .height(100.dp)
                                .fillMaxWidth()
                                .clickable {
                                    coroutineScope.launch {
                                        APPGlobalConfigStore.updateData {
                                            it
                                                .toBuilder()
                                                .setThemeColor(themeColor.key.toHexString())
                                                .build()
                                        }
                                        switchAppTheme(themeColor.key)
                                    }


                                },
                            color = Color.White
                        )
                    }
                }
            }

        }
    }

    fun Color.toHexString(): String {
        return String.format(
            "#%02X%02X%02X%02X",
            (this.alpha * 255).toInt(),
            (this.red * 255).toInt(),
            (this.green * 255).toInt(),
            (this.blue * 255).toInt()
        )
    }
}