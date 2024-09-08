package com.wan.android.compose.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

/**
 * @Description:
 * @Date: 2024/9/5 11:16
 * @author:  liuwenjie09
 * @version: 1.0
 */


// 可以使用下面的正则替换
// "(.*)":\s"#(.*)"
// $1=Color(0xFF$2)

/**
 * @Description:
 * @Date: 2024/9/5 16:41
 * @author:  liuwenjie09
 * @version: 1.0
 *
 *     primary: Color, // 主要颜色，用于应用的界面主元素
 *     onPrimary: Color, // 主要颜色上的文本或图标颜色
 *     primaryContainer: Color, // 主要颜色的容器颜色，通常用于按钮或卡片的背景色。
 *     onPrimaryContainer: Color, // 主要颜色容器上的文本或图标颜色。
 *     inversePrimary: Color, //  主要颜色的反转版，通常用于深色模式下的主要颜色。
 *     secondary: Color, // 次要颜色，用于次要界面元素。
 *     onSecondary: Color, // 次要颜色上的文本或图标颜色。
 *     secondaryContainer: Color, // 次要颜色的容器颜色。
 *     onSecondaryContainer: Color, // 次要颜色容器上的文本或图标颜色
 *     tertiary: Color, // 第三级颜色，用于辅助界面元素
 *     onTertiary: Color, // 第三级颜色上的文本或图标颜色
 *     tertiaryContainer: Color, // 第三级颜色的容器颜色
 *     onTertiaryContainer: Color, // 第三级颜色容器上的文本或图标颜色
 *     background: Color, // 应用的背景颜色
 *     onBackground: Color, // 背景上的文本或图标颜色
 *     surface: Color, // 表面的颜色，用于卡片、对话框等的背景色。
 *     onSurface: Color, // 表面上的文本或图标颜色。
 *     surfaceVariant: Color, // 表面的变体颜色。
 *     onSurfaceVariant: Color, // 表面变体上的文本或图标颜色
 *     surfaceTint: Color, // 用于给表面涂色的颜色，通常与主要颜色一致
 *     inverseSurface: Color, // 表面的反转版颜色，通常用于深色模式下的表面颜色
 *     inverseOnSurface: Color, // 反转表面上的文本或图标颜色。
 *     error: Color, // 错误状态下使用的颜色
 *     onError: Color, // 错误状态下的文本或图标颜色。
 *     errorContainer: Color, // 错误状态的容器颜色
 *     onErrorContainer: Color, // 错误状态容器上的文本或图标颜色。
 *     outline: Color, // 用于边框或分隔线的颜色
 *     outlineVariant: Color, // 边框或分隔线的变体颜色。
 *     scrim: Color, // 用于遮罩的颜色，通常用于模态对话框后面的背景。
 *     surfaceBright: Color, // 明亮的表面颜色
 *     surfaceDim: Color, // 昏暗的表面颜色。
 *     surfaceContainer: Color, // 容器的表面颜色
 *     surfaceContainerHigh: Color, //高亮的容器表面颜色
 *     surfaceContainerHighest: Color, // 最高亮的容器表面颜色
 *     surfaceContainerLow: Color, // 低亮的容器表面颜色
 *     surfaceContainerLowest: Color, // 最低亮的容器表面颜色
 *
 */
class ColorPalette(
    primary0: Color,
    primary5: Color,
    primary10: Color,
    primary15: Color,
    primary20: Color,
    primary25: Color,
    primary30: Color,
    primary35: Color,
    primary40: Color,
    primary50: Color,
    primary60: Color,
    primary70: Color,
    primary80: Color,
    primary90: Color,
    primary95: Color,
    primary98: Color,
    primary99: Color,
    primary100: Color,
    secondary0: Color,
    secondary5: Color,
    secondary10: Color,
    secondary15: Color,
    secondary20: Color,
    secondary25: Color,
    secondary30: Color,
    secondary35: Color,
    secondary40: Color,
    secondary50: Color,
    secondary60: Color,
    secondary70: Color,
    secondary80: Color,
    secondary90: Color,
    secondary95: Color,
    secondary98: Color,
    secondary99: Color,
    secondary100: Color,
    tertiary0: Color,
    tertiary5: Color,
    tertiary10: Color,
    tertiary15: Color,
    tertiary20: Color,
    tertiary25: Color,
    tertiary30: Color,
    tertiary35: Color,
    tertiary40: Color,
    tertiary50: Color,
    tertiary60: Color,
    tertiary70: Color,
    tertiary80: Color,
    tertiary90: Color,
    tertiary95: Color,
    tertiary98: Color,
    tertiary99: Color,
    tertiary100: Color,
    neutral0: Color,
    neutral5: Color,
    neutral10: Color,
    neutral15: Color,
    neutral20: Color,
    neutral25: Color,
    neutral30: Color,
    neutral35: Color,
    neutral40: Color,
    neutral50: Color,
    neutral60: Color,
    neutral70: Color,
    neutral80: Color,
    neutral90: Color,
    neutral95: Color,
    neutral98: Color,
    neutral99: Color,
    neutral100: Color,
    neutralVariant0: Color,
    neutralVariant5: Color,
    neutralVariant10: Color,
    neutralVariant15: Color,
    neutralVariant20: Color,
    neutralVariant25: Color,
    neutralVariant30: Color,
    neutralVariant35: Color,
    neutralVariant40: Color,
    neutralVariant50: Color,
    neutralVariant60: Color,
    neutralVariant70: Color,
    neutralVariant80: Color,
    neutralVariant90: Color,
    neutralVariant95: Color,
    neutralVariant98: Color,
    neutralVariant99: Color,
    neutralVariant100: Color,
) {

    var primary0 by mutableStateOf(primary0)
        private set
    var primary5 by mutableStateOf(primary5)
        private set
    var primary10 by mutableStateOf(primary10)
        private set
    var primary15 by mutableStateOf(primary15)
        private set
    var primary20 by mutableStateOf(primary20)
        private set
    var primary25 by mutableStateOf(primary25)
        private set
    var primary30 by mutableStateOf(primary30)
        private set
    var primary35 by mutableStateOf(primary35)
        private set
    var primary40 by mutableStateOf(primary40)
        private set
    var primary50 by mutableStateOf(primary50)
        private set
    var primary60 by mutableStateOf(primary60)
        private set
    var primary70 by mutableStateOf(primary70)
        private set
    var primary80 by mutableStateOf(primary80)
        private set
    var primary90 by mutableStateOf(primary90)
        private set
    var primary95 by mutableStateOf(primary95)
        private set
    var primary98 by mutableStateOf(primary98)
        private set
    var primary99 by mutableStateOf(primary99)
        private set
    var primary100 by mutableStateOf(primary100)
        private set
    var secondary0 by mutableStateOf(secondary0)
        private set
    var secondary5 by mutableStateOf(secondary5)
        private set
    var secondary10 by mutableStateOf(secondary10)
        private set
    var secondary15 by mutableStateOf(secondary15)
        private set
    var secondary20 by mutableStateOf(secondary20)
        private set
    var secondary25 by mutableStateOf(secondary25)
        private set
    var secondary30 by mutableStateOf(secondary30)
        private set
    var secondary35 by mutableStateOf(secondary35)
        private set
    var secondary40 by mutableStateOf(secondary40)
        private set
    var secondary50 by mutableStateOf(secondary50)
        private set
    var secondary60 by mutableStateOf(secondary60)
        private set
    var secondary70 by mutableStateOf(secondary70)
        private set
    var secondary80 by mutableStateOf(secondary80)
        private set
    var secondary90 by mutableStateOf(secondary90)
        private set
    var secondary95 by mutableStateOf(secondary95)
        private set
    var secondary98 by mutableStateOf(secondary98)
        private set
    var secondary99 by mutableStateOf(secondary99)
        private set
    var secondary100 by mutableStateOf(secondary100)
        private set
    var tertiary0 by mutableStateOf(tertiary0)
        private set
    var tertiary5 by mutableStateOf(tertiary5)
        private set
    var tertiary10 by mutableStateOf(tertiary10)
        private set
    var tertiary15 by mutableStateOf(tertiary15)
        private set
    var tertiary20 by mutableStateOf(tertiary20)
        private set
    var tertiary25 by mutableStateOf(tertiary25)
        private set
    var tertiary30 by mutableStateOf(tertiary30)
        private set
    var tertiary35 by mutableStateOf(tertiary35)
        private set
    var tertiary40 by mutableStateOf(tertiary40)
        private set
    var tertiary50 by mutableStateOf(tertiary50)
        private set
    var tertiary60 by mutableStateOf(tertiary60)
        private set
    var tertiary70 by mutableStateOf(tertiary70)
        private set
    var tertiary80 by mutableStateOf(tertiary80)
        private set
    var tertiary90 by mutableStateOf(tertiary90)
        private set
    var tertiary95 by mutableStateOf(tertiary95)
        private set
    var tertiary98 by mutableStateOf(tertiary98)
        private set
    var tertiary99 by mutableStateOf(tertiary99)
        private set
    var tertiary100 by mutableStateOf(tertiary100)
        private set
    var neutral0 by mutableStateOf(neutral0)
        private set
    var neutral5 by mutableStateOf(neutral5)
        private set
    var neutral10 by mutableStateOf(neutral10)
        private set
    var neutral15 by mutableStateOf(neutral15)
        private set
    var neutral20 by mutableStateOf(neutral20)
        private set
    var neutral25 by mutableStateOf(neutral25)
        private set
    var neutral30 by mutableStateOf(neutral30)
        private set
    var neutral35 by mutableStateOf(neutral35)
        private set
    var neutral40 by mutableStateOf(neutral40)
        private set
    var neutral50 by mutableStateOf(neutral50)
        private set
    var neutral60 by mutableStateOf(neutral60)
        private set
    var neutral70 by mutableStateOf(neutral70)
        private set
    var neutral80 by mutableStateOf(neutral80)
        private set
    var neutral90 by mutableStateOf(neutral90)
        private set
    var neutral95 by mutableStateOf(neutral95)
        private set
    var neutral98 by mutableStateOf(neutral98)
        private set
    var neutral99 by mutableStateOf(neutral99)
        private set
    var neutral100 by mutableStateOf(neutral100)
        private set
    var neutralVariant0 by mutableStateOf(neutralVariant0)
        private set
    var neutralVariant5 by mutableStateOf(neutralVariant5)
        private set
    var neutralVariant10 by mutableStateOf(neutralVariant10)
        private set
    var neutralVariant15 by mutableStateOf(neutralVariant15)
        private set
    var neutralVariant20 by mutableStateOf(neutralVariant20)
        private set
    var neutralVariant25 by mutableStateOf(neutralVariant25)
        private set
    var neutralVariant30 by mutableStateOf(neutralVariant30)
        private set
    var neutralVariant35 by mutableStateOf(neutralVariant35)
        private set
    var neutralVariant40 by mutableStateOf(neutralVariant40)
        private set
    var neutralVariant50 by mutableStateOf(neutralVariant50)
        private set
    var neutralVariant60 by mutableStateOf(neutralVariant60)
        private set
    var neutralVariant70 by mutableStateOf(neutralVariant70)
        private set
    var neutralVariant80 by mutableStateOf(neutralVariant80)
        private set
    var neutralVariant90 by mutableStateOf(neutralVariant90)
        private set
    var neutralVariant95 by mutableStateOf(neutralVariant95)
        private set
    var neutralVariant98 by mutableStateOf(neutralVariant98)
        private set
    var neutralVariant99 by mutableStateOf(neutralVariant99)
        private set
    var neutralVariant100 by mutableStateOf(neutralVariant100)
        private set
}

class CustomColorScheme(
    shadow: Color,
    primaryFixed: Color,
    onPrimaryFixed: Color,
    primaryFixedDim: Color,
    onPrimaryFixedVariant: Color,
    secondaryFixed: Color,
    onSecondaryFixed: Color,
    secondaryFixedDim: Color,
    onSecondaryFixedVariant: Color,
    tertiaryFixed: Color,
    onTertiaryFixed: Color,
    tertiaryFixedDim: Color,
    onTertiaryFixedVariant: Color,
) {
    var shadow by mutableStateOf(shadow)
        private set
    var primaryFixed by mutableStateOf(primaryFixed)
        private set
    var onPrimaryFixed by mutableStateOf(onPrimaryFixed)
        private set
    var primaryFixedDim by mutableStateOf(primaryFixedDim)
        private set
    var onPrimaryFixedVariant by mutableStateOf(onPrimaryFixedVariant)
        private set
    var secondaryFixed by mutableStateOf(secondaryFixed)
        private set
    var onSecondaryFixed by mutableStateOf(onSecondaryFixed)
        private set
    var secondaryFixedDim by mutableStateOf(secondaryFixedDim)
        private set
    var onSecondaryFixedVariant by mutableStateOf(onSecondaryFixedVariant)
        private set
    var tertiaryFixed by mutableStateOf(tertiaryFixed)
        private set
    var onTertiaryFixed by mutableStateOf(onTertiaryFixed)
        private set
    var tertiaryFixedDim by mutableStateOf(tertiaryFixedDim)
        private set
    var onTertiaryFixedVariant by mutableStateOf(onTertiaryFixedVariant)
        private set


    fun update(colors: CustomColorScheme) {
        this.shadow = colors.shadow
        this.primaryFixed = colors.primaryFixed
        this.onPrimaryFixed = colors.onPrimaryFixed
        this.primaryFixedDim = colors.primaryFixedDim
        this.onPrimaryFixedVariant = colors.onPrimaryFixedVariant
        this.secondaryFixed = colors.secondaryFixed
        this.onSecondaryFixed = colors.onSecondaryFixed
        this.secondaryFixedDim = colors.secondaryFixedDim
        this.onSecondaryFixedVariant = colors.onSecondaryFixedVariant
        this.tertiaryFixed = colors.tertiaryFixed
        this.onTertiaryFixed = colors.onTertiaryFixed
        this.tertiaryFixedDim = colors.tertiaryFixedDim
        this.onTertiaryFixedVariant = colors.onTertiaryFixedVariant
    }

    fun copy() = CustomColorScheme(
        shadow = this.shadow,
        primaryFixed = this.primaryFixed,
        onPrimaryFixed = this.onPrimaryFixed,
        primaryFixedDim = this.primaryFixedDim,
        onPrimaryFixedVariant = this.onPrimaryFixedVariant,
        secondaryFixed = this.secondaryFixed,
        onSecondaryFixed = this.onSecondaryFixed,
        secondaryFixedDim = this.secondaryFixedDim,
        onSecondaryFixedVariant = this.onSecondaryFixedVariant,
        tertiaryFixed = this.tertiaryFixed,
        onTertiaryFixed = this.onTertiaryFixed,
        tertiaryFixedDim = this.tertiaryFixedDim,
        onTertiaryFixedVariant = this.onTertiaryFixedVariant,
    )
}

@Stable
class AppThemeColor(
    isLight: Boolean,// 是否是浅色主题
    seedColor: Color, // 种子颜色
    colorPalette: ColorPalette,// 画板
    colorScheme: ColorScheme, // Material theme 颜色
    customColorScheme: CustomColorScheme, // 自定义颜色

) {
    var isLight by mutableStateOf(isLight)
        private set
    var seedColor by mutableStateOf(seedColor)
        private set
    var colorScheme by mutableStateOf(colorScheme)
        private set
    var customColorScheme by mutableStateOf(customColorScheme)
        private set
    var colorPalette by mutableStateOf(colorPalette)
        private set

    fun update(colors: AppThemeColor) {
        this.isLight = colors.isLight
        this.seedColor = colors.seedColor
        this.customColorScheme = colors.customColorScheme
        this.customColorScheme.update(colors.customColorScheme)
    }

    fun copy() = AppThemeColor(
        isLight = this.isLight,
        seedColor = this.seedColor,
        colorPalette = this.colorPalette,
        colorScheme = this.colorScheme,
        customColorScheme = this.customColorScheme.copy(),
    )
}

class ThemeColor(
    val seedColor: Color, // 种子颜色
    val lightTheme: AppThemeColor,
    val darkTheme: AppThemeColor
)