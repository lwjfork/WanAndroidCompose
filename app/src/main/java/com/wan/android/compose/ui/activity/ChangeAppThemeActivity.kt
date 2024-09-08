package com.wan.android.compose.ui.activity

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.wan.android.compose.component.ImmersiveScreenPageContent
import com.wan.android.compose.ui.theme.ThemeColors
import com.wan.android.compose.ui.theme.switchAppTheme

class ChangeAppThemeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImmersiveScreenPageContent{
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
                                    switchAppTheme(themeColor.key)
                                },
                            color = Color.White
                        )
                    }
                }
            }

        }
    }
}

