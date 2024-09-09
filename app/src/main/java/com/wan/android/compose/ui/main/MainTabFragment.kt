package com.wan.android.compose.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wan.android.compose.component.CircularProgress
import com.wan.android.compose.ui.main.vm.MainTabViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @Description:
 * @Date: 2024/9/9 13:26
 * @author:  liuwenjie09
 * @version: 1.0
 */
class MainTabFragment : Fragment() {

    val mainTabViewModel: MainTabViewModel
        get() = ViewModelProvider(this@MainTabFragment).get(MainTabViewModel::class.java)
    var state: LazyListState? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("TAG","HH")
        return ComposeView(inflater.context, null, 0).apply {
            this.setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(this@MainTabFragment.viewLifecycleOwner))
            val mainTabViewModel: MainTabViewModel = ViewModelProvider(this@MainTabFragment).get(MainTabViewModel::class.java)
            setContent {
                val loading: State<Boolean> =
                    mainTabViewModel.initLoading.observeAsState(false)
                mainTabViewModel.initListState(rememberLazyListState())
                val coroutineScope = rememberCoroutineScope()
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    LaunchedEffect(Unit) {
                        if (loading.value) {
                            coroutineScope.launch {
                                delay(3000)
                                mainTabViewModel.updateInitLoading(false)
                            }
                        }
                    }
                    if (loading.value) {
                        CircularProgress()
                    } else {
                        val itemsList = (0..100).toList()
                        val itemsIndexedList = listOf(
                            "A",
                            "B",
                            "C"
                        )
                        LazyColumn() {
                            items(itemsList) { Text("Item is $it") }
                            item { Text("Single item") }
                            itemsIndexed(itemsIndexedList) { index, item ->
                                ; Text(
                                "Item at index $index is $item"
                            )
                            }
                        }
                    }

                }
            }
        }
    }


    @Composable
    fun Banner(){
        val pageState = rememberPagerState {
            100
        }
        HorizontalPager(state = pageState, modifier = Modifier.fillMaxWidth().height(150.dp)) {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        state = null
//        mainTabViewModel.updateInitLoading(true)
    }

}