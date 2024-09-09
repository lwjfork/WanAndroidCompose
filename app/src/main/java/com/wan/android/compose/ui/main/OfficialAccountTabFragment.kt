package com.wan.android.compose.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

/**
 * @Description:
 * @Date: 2024/9/9 13:26
 * @author:  liuwenjie09
 * @version: 1.0
 */
class OfficialAccountTabFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(inflater.context, null, 0).apply {
            setContent {
                TabContent()
            }
        }
    }

    @Composable
    fun TabContent() {
        val tabName = this.javaClass.simpleName
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = tabName, color = Color.Red)

        }
    }
}