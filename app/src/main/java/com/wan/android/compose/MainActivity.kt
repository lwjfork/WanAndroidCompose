package com.wan.android.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.wan.android.compose.component.BottomTab
import com.wan.android.compose.component.SafePageContent
import com.wan.android.compose.ui.theme.AppTheme
import com.wan.android.compose.ui.theme.GreenTheme
import com.wan.android.compose.ui.theme.RedTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        hideNavigationBar()
        setContent {
            val statusBarColor = remember {
                mutableStateOf(Color.Green)
            }
            val isDartStyle = remember {
                mutableStateOf(true)
            }
            SafePageContent(bottomBar = { BottomBar()}, statusBarColor = statusBarColor.value, statusBarIconDarkStyle = isDartStyle.value){
                Column(modifier = Modifier
                    .fillMaxSize()) {
                    Text(
                        text = "切换绿色", modifier = Modifier
                            .background(Color.Green)
                            .height(100.dp)
                            .fillMaxWidth()
                            .clickable {
                                AppTheme.switch(GreenTheme)
                                statusBarColor.value = Color.Green
                                isDartStyle.value = true
                            },
                        color = Color.White
                    )
                    Text(
                        text = "切换红色", modifier = Modifier
                            .background(Color.Red)
                            .height(100.dp)
                            .fillMaxWidth()
                            .clickable {
                                AppTheme.switch(RedTheme)
                                statusBarColor.value = Color.Red
                                isDartStyle.value = false
                            },
                        color = Color.White
                    )
                }
            }

        }
    }

    fun hideNavigationBar() {
        val controller = WindowCompat.getInsetsController(window, window.decorView)
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        controller.hide(WindowInsetsCompat.Type.navigationBars())
    }


    @Composable
    fun APP(){

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