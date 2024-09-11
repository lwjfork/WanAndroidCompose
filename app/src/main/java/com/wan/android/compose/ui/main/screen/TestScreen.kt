package com.wan.android.compose.ui.main.screen

import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomAppBarState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.compose.rememberNavController
import com.wan.android.compose.R
import com.wan.android.compose.component.ImmersiveScreenPageContent

/**
 * @Description:
 * @Date: 2024/9/10 20:30
 * @author:  liuwenjie09
 * @version: 1.0
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestScreen() {
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
        gesturesEnabled = false,
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




