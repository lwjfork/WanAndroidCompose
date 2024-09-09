@file:Suppress("FunctionName")

package com.wan.android.compose.component

import android.content.Context
import android.util.AttributeSet
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.lifecycle.LifecycleOwner

/**
 * @Description:
 * @Date: 2024/9/9 18:12
 * @author:  liuwenjie09
 * @version: 1.0
 */
// DisposeOnLifecycleDestroyed 跟随 Fragment的生命周期进行是否重绘
// 如果执行了 onDestroy 后，再切换回来tab，ComposeView 就会进行重新组合
fun DisposeOnLifecycleComposeView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    viewLifecycleOwner: LifecycleOwner? = null
): ComposeView {
    return ComposeView(context, attrs, defStyleAttr).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
//        viewLifecycleOwner?.let {
//            this.setViewCompositionStrategy(
//                ViewCompositionStrategy.DisposeOnLifecycleDestroyed(
//                    viewLifecycleOwner
//                )
//            )
//        }
    }

}