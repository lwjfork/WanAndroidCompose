package com.wan.android.compose.ui.main.screen

import android.app.Activity
import android.content.Intent
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberBottomAppBarState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
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
import com.wan.android.compose.ui.activity.change_app_theme.ChangeAppThemeActivity
import com.wan.android.compose.ui.main.tabs.MainTabScreen
import com.wan.android.compose.ui.main.tabs.OfficialAccountTabScreen
import com.wan.android.compose.ui.main.tabs.ProjectTabScreen
import com.wan.android.compose.ui.main.tabs.SquareTabScreen
import kotlinx.coroutines.launch

/**
 * @Description:
 * @Date: 2024/9/10 20:30
 * @author:  liuwenjie09
 * @version: 1.0
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val tabs: List<Pair<String, Int>> = mutableListOf(
        Pair("首页", R.drawable.ic_home_24dp),
        Pair("广场", R.drawable.ic_square_24dp),
        Pair("公众号", R.drawable.ic_wechat_24dp),
        Pair("体系", R.drawable.ic_apps_24dp),
        Pair("项目", R.drawable.ic_project_24dp),
    )
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val selectIndex = rememberSaveable { mutableIntStateOf(0) }
    val navController = rememberNavController()
    val topBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val bottomBarScrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior(
        rememberBottomAppBarState()
    )
    val modifier = Modifier
        .nestedScroll(bottomBarScrollBehavior.nestedScrollConnection)
        .nestedScroll(topBarScrollBehavior.nestedScrollConnection)
    ModalNavigationDrawer(drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            NavigationDrawerContent(drawerState)
        }) {
        ImmersiveScreenPageContent(
            modifier = modifier,
            topBar = { TopBar(drawerState, scrollBehavior = topBarScrollBehavior) },
            bottomBar = {
                BottomBar(tabs, selectIndex.intValue, bottomBarScrollBehavior) {
                    selectIndex.intValue = it
                    navController.navigate(tabs.get(it).first) {
                        popUpTo(tabs[0].first) {
                            saveState = true
                        }
                        // 避免多次点击Item时产生多个实列
                        launchSingleTop = true
                        // 当再次点击之前的Item时，恢复状态
                        restoreState = true
                    }
                }
            },
            content = { PageNavContent(navController, tabs) }
        )
    }
}


@Composable
fun PageNavContent(navHostController: NavHostController, tabs: List<Pair<String, Int>>) {
    NavHost(
        navController = navHostController,
        startDestination = tabs[0].first,
        enterTransition = { fadeIn(spring(stiffness = 0F)) },
        exitTransition = { fadeOut(spring(stiffness = 0F)) },
        popExitTransition = { fadeOut(spring(stiffness = 0F)) },
        popEnterTransition = { fadeIn(spring(stiffness = 0F)) },
    ) {
        composable(tabs[0].first) {
            MainTabScreen()
        }
        composable(tabs[1].first) {
            SquareTabScreen()
        }
        composable(tabs[2].first) {
            OfficialAccountTabScreen()
        }
        composable(tabs[3].first) {
            SquareTabScreen()
        }
        composable(tabs[4].first) {
            ProjectTabScreen()
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(drawerState: DrawerState? = null, scrollBehavior: TopAppBarScrollBehavior? = null) {
    val scope = rememberCoroutineScope()
    AppCenterAlignedTopAppBar(title = {
        Text(
            text = "标题", fontSize = 18.sp
        )
    }, navigationIcon = {
        AppTopBarIconContainer(painter = painterResource(id = R.drawable.ic_project_24dp),
            onClick = {
                scope.launch {
                    drawerState?.apply {
                        if (isClosed) open() else close()
                    }
                }
            })
    }, actions = {
        AppTopBarIconContainer(
            painter = painterResource(id = R.drawable.ic_project_24dp),
        )
        AppTopBarIconContainer(
            painter = painterResource(id = R.drawable.ic_project_24dp),
        )
    }, scrollBehavior = scrollBehavior)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(
    tabs: List<Pair<String, Int>>,
    selectIndex: Int,
    scrollBehavior: BottomAppBarScrollBehavior? = null,
    onSelectTab: (index: Int) -> Unit
) {
    val selectTab = rememberUpdatedState(newValue = onSelectTab)
    AppNavigationBar(
        tab = tabs,
        selectedIndex = selectIndex,
        selectedTextSize = 14.sp,
        unSelectedTextSize = 12.sp,
        onSelectTab = selectTab.value,
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun NavigationDrawerContent(
    drawerState: DrawerState,
) {
    val scope = rememberCoroutineScope()
    val activity = LocalContext.current as Activity
    ModalDrawerSheet(
        modifier = Modifier.width(300.coerceAtMost((LocalConfiguration.current.screenWidthDp * 0.6).toInt()).dp)
    ) {
        Text("Drawer title", modifier = Modifier.padding(16.dp))
        Divider()
        NavigationDrawerItem(label = { Text(text = "Drawer  关闭") },
            selected = false,
            onClick = {
                scope.launch {
                    drawerState.apply {
                        if (isClosed) open() else close()
                    }
                }
            })
        NavigationDrawerItem(label = { Text(text = "切换  主题") },
            selected = false,
            onClick = {
                val intent = Intent()
                intent.setClass(activity.application, ChangeAppThemeActivity::class.java)
                activity.startActivity(intent)
            })
    }
}