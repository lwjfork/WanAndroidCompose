package com.wan.android.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.wan.android.compose.ext.toHexString
import com.wan.android.compose.stores.memory.GlobalContext
import com.wan.android.compose.stores.persistent.APPGlobalConfigStore


@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val seedColor: Color = AppTheme.seedColor
    val (colorTheme, appColors) = seedColor.colors
    ProvideAppColors(colors = appColors) {
        MaterialTheme(
            colorScheme = appColors.colorScheme,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

val MaterialTheme.appColors: AppThemeColor
    @Composable
    get() = AppTheme.colors

private object AppTheme {
    val colors: AppThemeColor
        @Composable get() = LocalAppColors.current

    /** 使用一个state维护当前主题配置,这里的写法取决于具体业务，
    如果你使用了深色模式默认配置，则无需这个变量，即app只支持深色与亮色，
    那么只需要每次读系统配置即可。但是compose本身可以做到快速切换主题，
    那么维护一个变量是肯定没法避免的 */
    var seedColor: Color by mutableStateOf(Color(0xFF8F13A8))

    fun switch(seedColor: Color) {
        if (AppTheme.seedColor == seedColor) {
            return
        }
        AppTheme.seedColor = seedColor
    }
}

suspend fun switchAppTheme(seedColor: Color) {
    GlobalContext.getApplication().APPGlobalConfigStore.updateData {
        it.toBuilder()
            .setThemeColor(seedColor.toHexString())
            .build()
    }
    AppTheme.switch(seedColor)
}


@Composable
private fun ProvideAppColors(
    colors: AppThemeColor, content: @Composable () -> Unit
) {
    val colorPalette = remember {
        colors.copy()
    }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalAppColors provides colors, content = content)
}

private val LocalAppColors = staticCompositionLocalOf {
    ThemeColors.entries.iterator().next().value.lightTheme
}


/* 针对当前主题配置颜色板扩展属性 */
private val Color.colors: Pair<Color, AppThemeColor>
    @Composable
    get() = Pair(
        this, if (isSystemInDarkTheme()) {
            ThemeColors.get(this)!!.darkTheme
        } else {
            ThemeColors.get(this)!!.lightTheme
        }
    )


val ThemeColors: LinkedHashMap<Color, ThemeColor> = LinkedHashMap(

)