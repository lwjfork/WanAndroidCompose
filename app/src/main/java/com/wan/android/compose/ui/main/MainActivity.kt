package com.wan.android.compose.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.wan.android.compose.R
import com.wan.android.compose.component.AppCenterAlignedTopAppBar
import com.wan.android.compose.component.AppNavigationBar
import com.wan.android.compose.component.AppTopBarIconContainer
import com.wan.android.compose.component.ImmersiveScreenPageContent
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


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
            val selectIndex = remember { mutableIntStateOf(1) }
            val tabs: List<Pair<String, Int>> = mutableListOf(
                Pair("首页", R.drawable.ic_home_24dp),
                Pair("广场", R.drawable.ic_square_24dp),
                Pair("公众号", R.drawable.ic_wechat_24dp),
                Pair("体系", R.drawable.ic_apps_24dp),
                Pair("项目", R.drawable.ic_project_24dp),
            )
            val fragments: List<Fragment> = mutableListOf(
                MainTabFragment(),
                SquareTabFragment(),
                OfficialAccountTabFragment(),
                SystemTabFragment(),
                ProjectTabFragment(),
            )
            ModalNavigationDrawer(drawerState = drawerState,
                gesturesEnabled = false,
                drawerContent = {
                    NavigationDrawerContent()
                }) {
                ImmersiveScreenPageContent(topBar = { TopBar(drawerState) }, bottomBar = {
                    BottomBar(tabs, selectIndex.value) {
                        selectIndex.value = it
                    }
                }) {
                    PageContent(fragments = fragments, selectIndex.value)
                }
            }
        }
    }

    class ViewPageFragmentAdapter(
        private val fragments: List<Fragment>, fragmentActivity: FragmentActivity
    ) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = this.fragments.size
        override fun createFragment(position: Int) = this.fragments.get(position)
    }


    @Composable
    fun PageContent(fragments: List<Fragment>, currentIndex: Int) {
        val activity = this
        AndroidView(factory = {
            ViewPager2(it).apply {
                this.adapter = ViewPageFragmentAdapter(fragments, activity)
                this.setCurrentItem(currentIndex,false)
                this.isUserInputEnabled = false
            }
        }, modifier = Modifier.fillMaxSize(), update = {
            it.setCurrentItem(currentIndex,false)
        })
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopBar(drawerState: DrawerState? = null) {
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
        })
    }

    @Composable
    fun BottomBar(
        tabs: List<Pair<String, Int>>, selectIndex: Int, onSelectTab: (index: Int) -> Unit
    ) {
        val selectTab = rememberUpdatedState(newValue = onSelectTab)
        AppNavigationBar(
            tab = tabs,
            selectedIndex = selectIndex,
            selectedTextSize = 14.sp,
            unSelectedTextSize = 12.sp,
            onSelectTab = selectTab.value
        )
    }

    @Composable
    fun NavigationDrawerContent(
        drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    ) {
        val scope = rememberCoroutineScope()
        ModalDrawerSheet(
            modifier = Modifier.width(300.coerceAtMost((LocalConfiguration.current.screenWidthDp * 0.6).toInt()).dp)
        ) {
            Text("Drawer title", modifier = Modifier.padding(16.dp))
            Divider()
            NavigationDrawerItem(label = { Text(text = "Drawer Item") },
                selected = false,
                onClick = {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                })
        }
    }
}