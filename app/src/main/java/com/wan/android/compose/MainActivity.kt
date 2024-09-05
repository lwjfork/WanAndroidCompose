package com.wan.android.compose

import android.content.Intent
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wan.android.compose.component.BottomTab
import com.wan.android.compose.component.ImmersiveScreenPageContent
import com.wan.android.compose.ui.activity.ChangeAppThemeActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImmersiveScreenPageContent(bottomBar = { BottomBar()}){
                Column(modifier = Modifier
                    .fillMaxSize()) {
                    Text(
                        text = "切换主题", modifier = Modifier
                            .background(Color.Green)
                            .height(100.dp)
                            .fillMaxWidth()
                            .clickable {
                                val intent = Intent()
                                intent.setClass(applicationContext,ChangeAppThemeActivity::class.java)
                                startActivity(intent)
                            },
                        color = Color.White
                    )
                }
            }

        }
    }





    @Composable
    @Preview
    fun BottomBar() {
        val index = remember { mutableIntStateOf(0) }
        BottomTab(
            mutableListOf(
                Pair("首页", R.drawable.ic_home_24dp),
                Pair("广场", R.drawable.ic_square_24dp),
                Pair("公众号", R.drawable.ic_wechat_24dp),
                Pair("体系", R.drawable.ic_apps_24dp),
                Pair("项目", R.drawable.ic_project_24dp),
            ),
            selectedIndex = index.intValue,
            onSelectTab = {
                index.value = it
            }
        )
    }
}