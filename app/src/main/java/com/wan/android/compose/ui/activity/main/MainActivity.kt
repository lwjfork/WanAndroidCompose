package com.wan.android.compose.ui.activity.main

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wan.android.compose.R
import com.wan.android.compose.component.AppCenterAlignedTopAppBar
import com.wan.android.compose.component.AppNavigationBar
import com.wan.android.compose.component.AppTopBarIconContainer
import com.wan.android.compose.component.ImmersiveScreenPageContent
import com.wan.android.compose.theme.readThemeFromAssets
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        readThemeFromAssets(this)
        setContent {
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            ModalNavigationDrawer(
                drawerState = drawerState,
                gesturesEnabled = true,
                drawerContent = { NavigationDrawerContent(drawerState) }
            ) {
                ImmersiveScreenPageContent(
                    topBar = { TopBar(drawerState) },
                    bottomBar = { BottomBar() }) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Text(
                            text = "切换主题", modifier = Modifier
                                .background(Color.Green)
                                .height(100.dp)
                                .fillMaxWidth()
                                .clickable {
                                    scope.launch {
                                        drawerState.apply {
                                            if (isClosed) open() else close()
                                        }
                                    }
                                },
                            color = Color.White
                        )
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopBar(drawerState: DrawerState? = null) {
        val scope = rememberCoroutineScope()
        AppCenterAlignedTopAppBar(
            title = {
                Text(
                    text = "标题",
                    fontSize = 18.sp
                )
            },
            navigationIcon = {
                AppTopBarIconContainer(
                    painter = painterResource(id = R.drawable.ic_project_24dp),
                    onClick = {
                        scope.launch {
                            drawerState?.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                )
            },
            actions = {
                AppTopBarIconContainer(
                    painter = painterResource(id = R.drawable.ic_project_24dp),
                )
                AppTopBarIconContainer(
                    painter = painterResource(id = R.drawable.ic_project_24dp),
                )
            }
        )
    }

    @Composable
    fun BottomBar() {
        val index = remember { mutableIntStateOf(0) }
        AppNavigationBar(
            mutableListOf(
                Pair("首页", R.drawable.ic_home_24dp),
                Pair("广场", R.drawable.ic_square_24dp),
                Pair("公众号", R.drawable.ic_wechat_24dp),
                Pair("体系", R.drawable.ic_apps_24dp),
                Pair("项目", R.drawable.ic_project_24dp),
            ),
            selectedIndex = index.intValue,
            selectedTextSize = 14.sp,
            unSelectedTextSize = 12.sp,
            onSelectTab = {
                index.intValue = it
            }
        )
    }
    @Composable
    fun NavigationDrawerContent(
        drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    ) {
        val scope = rememberCoroutineScope()
        ModalDrawerSheet(
            modifier = Modifier
                .width(300.coerceAtMost((LocalConfiguration.current.screenWidthDp * 0.6).toInt()).dp)
        ) {
            Text("Drawer title", modifier = Modifier.padding(16.dp))
            Divider()
            NavigationDrawerItem(
                label = { Text(text = "Drawer Item") },
                selected = false,
                onClick = {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }
            )
        }
    }
}