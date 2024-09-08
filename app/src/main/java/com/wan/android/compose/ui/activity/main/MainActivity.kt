package com.wan.android.compose.ui.activity.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wan.android.compose.R
import com.wan.android.compose.component.AppCenterAlignedTopAppBar
import com.wan.android.compose.component.AppNavigationBar
import com.wan.android.compose.component.AppTopBarIconContainer
import com.wan.android.compose.component.ImmersiveScreenPageContent
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    val tabs: List<Triple<String, Int, String>> = mutableListOf(
        Triple("首页", R.drawable.ic_home_24dp, "MainTabScreen"),
        Triple("广场", R.drawable.ic_square_24dp, "SquareTabScreen"),
        Triple("公众号", R.drawable.ic_wechat_24dp, "OfficialAccountTabScreen"),
        Triple("体系", R.drawable.ic_apps_24dp, "SystemTabScreen"),
        Triple("项目", R.drawable.ic_project_24dp, "ProjectTabScreen"),
    )


    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true
        }
        return super.onKeyUp(keyCode, event)
    }
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val selectIndex = remember { mutableStateOf((tabs[0])) }
            val navController = rememberNavController()
            ModalNavigationDrawer(
                drawerState = drawerState,
                gesturesEnabled = true,
                drawerContent = { NavigationDrawerContent(drawerState) }
            ) {
                ImmersiveScreenPageContent(
                    topBar = { TopBar(drawerState) },
                    bottomBar = {
                        BottomBar(selectIndex.value) {
                            selectIndex.value = it
                            navController.navigate(it.third)
                        }
                    }) {
                    PageContent(navController)
                }
            }
        }
    }


    @Composable
    fun PageContent(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = tabs[0].third,
            modifier = Modifier.fillMaxSize(),
            enterTransition = {
                fadeIn(spring(stiffness = 0f))
            },
            exitTransition = {
                fadeOut(spring(stiffness = 0f))
            },
            popEnterTransition = {
                fadeIn(spring(stiffness = 0f))
            },
            popExitTransition = {
                fadeOut(spring(stiffness = 0f))
            }
        ) {
            composable(route = tabs[0].third) {
                MainTabScreen()
            }
            composable(route = tabs[1].third) {
                SquareTabScreen()
            }
            composable(route = tabs[2].third) {
                OfficialAccountTabScreen()
            }
            composable(route = tabs[3].third) {
                SystemTabScreen()
            }
            composable(route = tabs[4].third) {
                ProjectTabScreen()
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
    fun BottomBar(
        selectIndex: Triple<String, Int, String>,
        onSelectTab: (index: Triple<String, Int, String>) -> Unit
    ) {
        AppNavigationBar(
            tab = tabs,
            selectedIndex = selectIndex,
            selectedTextSize = 14.sp,
            unSelectedTextSize = 12.sp,
            onSelectTab = onSelectTab
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