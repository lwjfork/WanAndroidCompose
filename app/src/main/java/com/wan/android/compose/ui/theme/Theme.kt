package com.wan.android.compose.ui.theme

import android.app.Activity
import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

sealed interface ThemeScheme
object GreenTheme:ThemeScheme
object RedTheme:ThemeScheme


@Composable
fun AppTheme(
    pallet: ThemeScheme = AppTheme.pallet, content: @Composable () -> Unit
) {
    val (colorTheme, appColors) = pallet.colors
    ProvideAppColors(colors = appColors) {
        MaterialTheme(
            colorScheme = lightColorScheme(),
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

val MaterialTheme.appColors:AppColors
    @Composable
    get() = AppTheme.colors

object AppTheme {
    val colors: AppColors
        @Composable get() = LocalAppColors.current

    /** 使用一个state维护当前主题配置,这里的写法取决于具体业务，
    如果你使用了深色模式默认配置，则无需这个变量，即app只支持深色与亮色，
    那么只需要每次读系统配置即可。但是compose本身可以做到快速切换主题，
    那么维护一个变量是肯定没法避免的 */
    var pallet:ThemeScheme by mutableStateOf(GreenTheme)

    fun switch(theme:ThemeScheme){
        if(theme == pallet){
            return
        }
        pallet = theme
    }
}

private val AppLightColorPalette = AppColors(
    isDark = false,
    statusBarColor = Color.Red,
    bottomTabSelectedColor = Color.Red,
    bottomTabDefaultColor = Color.Gray

)

private val AppDarkColorPalette = AppColors(
    isDark = true,
    statusBarColor = Color.Green,
    bottomTabSelectedColor = Color.Green,
    bottomTabDefaultColor = Color.Gray
)

@Composable
fun ProvideAppColors(
    colors: AppColors, content: @Composable () -> Unit
) {
    val colorPalette = remember {
        colors.copy()
    }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalAppColors provides colors, content = content)
}

private val LocalAppColors = staticCompositionLocalOf {
    AppLightColorPalette
}


/* 针对当前主题配置颜色板扩展属性 */
private val ThemeScheme.colors: Pair<ThemeScheme, AppColors>
    get() = when (this) {
        GreenTheme -> GreenTheme to AppDarkColorPalette
        RedTheme -> RedTheme to AppLightColorPalette
    }

