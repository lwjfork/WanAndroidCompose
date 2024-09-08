package com.wan.android.compose.theme

import android.content.Context
import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import org.json.JSONObject

/**
 * @Description:
 * @Date: 2024/9/6 15:19
 * @author:  liuwenjie09
 * @version: 1.0
 */


private fun parseColorPaletteFromJson(jsonObj: JSONObject): ColorPalette {
    val palettes = jsonObj.optJSONObject("palettes")
    val palettesColors: MutableMap<String, Color> = mutableMapOf()
    palettes?.let {
        it.keys().forEach { typeColorName ->
            val typeColorJSONObject = palettes.optJSONObject(typeColorName)
            typeColorJSONObject?.keys()?.forEach {
                palettesColors["${toCamelCase(typeColorName)}${it}"] =
                    toColor(typeColorJSONObject.optString(it))
            }
        }
    }
    return ColorPalette(
        primary0 = palettesColors["primary0"]!!,
        primary5 = palettesColors["primary5"]!!,
        primary10 = palettesColors["primary10"]!!,
        primary15 = palettesColors["primary15"]!!,
        primary20 = palettesColors["primary20"]!!,
        primary25 = palettesColors["primary25"]!!,
        primary30 = palettesColors["primary30"]!!,
        primary35 = palettesColors["primary35"]!!,
        primary40 = palettesColors["primary40"]!!,
        primary50 = palettesColors["primary50"]!!,
        primary60 = palettesColors["primary60"]!!,
        primary70 = palettesColors["primary70"]!!,
        primary80 = palettesColors["primary80"]!!,
        primary90 = palettesColors["primary90"]!!,
        primary95 = palettesColors["primary95"]!!,
        primary98 = palettesColors["primary98"]!!,
        primary99 = palettesColors["primary99"]!!,
        primary100 = palettesColors["primary100"]!!,
        secondary0 = palettesColors["secondary0"]!!,
        secondary5 = palettesColors["secondary5"]!!,
        secondary10 = palettesColors["secondary10"]!!,
        secondary15 = palettesColors["secondary15"]!!,
        secondary20 = palettesColors["secondary20"]!!,
        secondary25 = palettesColors["secondary25"]!!,
        secondary30 = palettesColors["secondary30"]!!,
        secondary35 = palettesColors["secondary35"]!!,
        secondary40 = palettesColors["secondary40"]!!,
        secondary50 = palettesColors["secondary50"]!!,
        secondary60 = palettesColors["secondary60"]!!,
        secondary70 = palettesColors["secondary70"]!!,
        secondary80 = palettesColors["secondary80"]!!,
        secondary90 = palettesColors["secondary90"]!!,
        secondary95 = palettesColors["secondary95"]!!,
        secondary98 = palettesColors["secondary98"]!!,
        secondary99 = palettesColors["secondary99"]!!,
        secondary100 = palettesColors["secondary100"]!!,
        tertiary0 = palettesColors["tertiary0"]!!,
        tertiary5 = palettesColors["tertiary5"]!!,
        tertiary10 = palettesColors["tertiary10"]!!,
        tertiary15 = palettesColors["tertiary15"]!!,
        tertiary20 = palettesColors["tertiary20"]!!,
        tertiary25 = palettesColors["tertiary25"]!!,
        tertiary30 = palettesColors["tertiary30"]!!,
        tertiary35 = palettesColors["tertiary35"]!!,
        tertiary40 = palettesColors["tertiary40"]!!,
        tertiary50 = palettesColors["tertiary50"]!!,
        tertiary60 = palettesColors["tertiary60"]!!,
        tertiary70 = palettesColors["tertiary70"]!!,
        tertiary80 = palettesColors["tertiary80"]!!,
        tertiary90 = palettesColors["tertiary90"]!!,
        tertiary95 = palettesColors["tertiary95"]!!,
        tertiary98 = palettesColors["tertiary98"]!!,
        tertiary99 = palettesColors["tertiary99"]!!,
        tertiary100 = palettesColors["tertiary100"]!!,
        neutral0 = palettesColors["neutral0"]!!,
        neutral5 = palettesColors["neutral5"]!!,
        neutral10 = palettesColors["neutral10"]!!,
        neutral15 = palettesColors["neutral15"]!!,
        neutral20 = palettesColors["neutral20"]!!,
        neutral25 = palettesColors["neutral25"]!!,
        neutral30 = palettesColors["neutral30"]!!,
        neutral35 = palettesColors["neutral35"]!!,
        neutral40 = palettesColors["neutral40"]!!,
        neutral50 = palettesColors["neutral50"]!!,
        neutral60 = palettesColors["neutral60"]!!,
        neutral70 = palettesColors["neutral70"]!!,
        neutral80 = palettesColors["neutral80"]!!,
        neutral90 = palettesColors["neutral90"]!!,
        neutral95 = palettesColors["neutral95"]!!,
        neutral98 = palettesColors["neutral98"]!!,
        neutral99 = palettesColors["neutral99"]!!,
        neutral100 = palettesColors["neutral100"]!!,
        neutralVariant0 = palettesColors["neutralVariant0"]!!,
        neutralVariant5 = palettesColors["neutralVariant5"]!!,
        neutralVariant10 = palettesColors["neutralVariant10"]!!,
        neutralVariant15 = palettesColors["neutralVariant15"]!!,
        neutralVariant20 = palettesColors["neutralVariant20"]!!,
        neutralVariant25 = palettesColors["neutralVariant25"]!!,
        neutralVariant30 = palettesColors["neutralVariant30"]!!,
        neutralVariant35 = palettesColors["neutralVariant35"]!!,
        neutralVariant40 = palettesColors["neutralVariant40"]!!,
        neutralVariant50 = palettesColors["neutralVariant50"]!!,
        neutralVariant60 = palettesColors["neutralVariant60"]!!,
        neutralVariant70 = palettesColors["neutralVariant70"]!!,
        neutralVariant80 = palettesColors["neutralVariant80"]!!,
        neutralVariant90 = palettesColors["neutralVariant90"]!!,
        neutralVariant95 = palettesColors["neutralVariant95"]!!,
        neutralVariant98 = palettesColors["neutralVariant98"]!!,
        neutralVariant99 = palettesColors["neutralVariant99"]!!,
        neutralVariant100 = palettesColors["neutralVariant100"]!!
    )
}

fun parseThemeSpecFromJson(
    jsonObj: JSONObject, isLight: Boolean, seedColor: Color, colorPalette: ColorPalette
): AppThemeColor {
    val materialColorName: Array<String> = arrayOf(
        "primary",
        "onPrimary",
        "primaryContainer",
        "onPrimaryContainer",
        "inversePrimary",
        "secondary",
        "onSecondary",
        "secondaryContainer",
        "onSecondaryContainer",
        "tertiary",
        "onTertiary",
        "tertiaryContainer",
        "onTertiaryContainer",
        "background",
        "onBackground",
        "surface",
        "onSurface",
        "surfaceVariant",
        "onSurfaceVariant",
        "surfaceTint",
        "inverseSurface",
        "inverseOnSurface",
        "error",
        "onError",
        "errorContainer",
        "onErrorContainer",
        "outline",
        "outlineVariant",
        "scrim",
        "surfaceBright",
        "surfaceDim",
        "surfaceContainer",
        "surfaceContainerHigh",
        "surfaceContainerHighest",
        "surfaceContainerLow",
        "surfaceContainerLowest"
    )
    val customColorNames: Array<String> = arrayOf(
        "shadow",
        "primaryFixed",
        "onPrimaryFixed",
        "primaryFixedDim",
        "onPrimaryFixedVariant",
        "secondaryFixed",
        "onSecondaryFixed",
        "secondaryFixedDim",
        "onSecondaryFixedVariant",
        "tertiaryFixed",
        "onTertiaryFixed",
        "tertiaryFixedDim",
        "onTertiaryFixedVariant"
    )
    val colors: MutableMap<String, Color> = mutableMapOf()
    jsonObj.keys().forEach {
        colors[it] = toColor(jsonObj.optString(it))
    }

    return AppThemeColor(
        isLight, seedColor, colorPalette, colorScheme = ColorScheme(
            primary = colors["primary"]!!,
            onPrimary = colors["onPrimary"]!!,
            primaryContainer = colors["primaryContainer"]!!,
            onPrimaryContainer = colors["onPrimaryContainer"]!!,
            inversePrimary = colors["inversePrimary"]!!,
            secondary = colors["secondary"]!!,
            onSecondary = colors["onSecondary"]!!,
            secondaryContainer = colors["secondaryContainer"]!!,
            onSecondaryContainer = colors["onSecondaryContainer"]!!,
            tertiary = colors["tertiary"]!!,
            onTertiary = colors["onTertiary"]!!,
            tertiaryContainer = colors["tertiaryContainer"]!!,
            onTertiaryContainer = colors["onTertiaryContainer"]!!,
            background = colors["background"]!!,
            onBackground = colors["onBackground"]!!,
            surface = colors["surface"]!!,
            onSurface = colors["onSurface"]!!,
            surfaceVariant = colors["surfaceVariant"]!!,
            onSurfaceVariant = colors["onSurfaceVariant"]!!,
            surfaceTint = colors["surfaceTint"]!!,
            inverseSurface = colors["inverseSurface"]!!,
            inverseOnSurface = colors["inverseOnSurface"]!!,
            error = colors["error"]!!,
            onError = colors["onError"]!!,
            errorContainer = colors["errorContainer"]!!,
            onErrorContainer = colors["onErrorContainer"]!!,
            outline = colors["outline"]!!,
            outlineVariant = colors["outlineVariant"]!!,
            scrim = colors["scrim"]!!,
            surfaceBright = colors["surfaceBright"]!!,
            surfaceDim = colors["surfaceDim"]!!,
            surfaceContainer = colors["surfaceContainer"]!!,
            surfaceContainerHigh = colors["surfaceContainerHigh"]!!,
            surfaceContainerHighest = colors["surfaceContainerHighest"]!!,
            surfaceContainerLow = colors["surfaceContainerLow"]!!,
            surfaceContainerLowest = colors["surfaceContainerLowest"]!!,
        ), customColorScheme = CustomColorScheme(
            shadow = colors["shadow"]!!,
            primaryFixed = colors["primaryFixed"]!!,
            onPrimaryFixed = colors["onPrimaryFixed"]!!,
            primaryFixedDim = colors["primaryFixedDim"]!!,
            onPrimaryFixedVariant = colors["onPrimaryFixedVariant"]!!,
            secondaryFixed = colors["secondaryFixed"]!!,
            onSecondaryFixed = colors["onSecondaryFixed"]!!,
            secondaryFixedDim = colors["secondaryFixedDim"]!!,
            onSecondaryFixedVariant = colors["onSecondaryFixedVariant"]!!,
            tertiaryFixed = colors["tertiaryFixed"]!!,
            onTertiaryFixed = colors["onTertiaryFixed"]!!,
            tertiaryFixedDim = colors["tertiaryFixedDim"]!!,
            onTertiaryFixedVariant = colors["onTertiaryFixedVariant"]!!,
        )
    )
}

fun parseThemeFromJson(context: Context, assetFileName: String): ThemeColor? {
    try {
        val themeJson = context.assets.open(assetFileName).bufferedReader().readText()
        val jsonObj = JSONObject(themeJson)
        val colorPalette = parseColorPaletteFromJson(jsonObj)
        val seedColor = toColor(jsonObj.optString("seed"))
        var lightThemeColor: AppThemeColor? = null
        var darkThemeColor: AppThemeColor? = null
        val schemesJsonObj = jsonObj.optJSONObject("schemes")
        schemesJsonObj?.keys()?.forEach {
            if (it == "light") {
                lightThemeColor = parseThemeSpecFromJson(
                    schemesJsonObj.optJSONObject(it)!!, true, seedColor, colorPalette
                )
            } else if (it == "dark") {
                darkThemeColor = parseThemeSpecFromJson(
                    schemesJsonObj.optJSONObject(it)!!, false, seedColor, colorPalette
                )
            }
        }

        return ThemeColor(
            seedColor, lightTheme = lightThemeColor!!, darkTheme = darkThemeColor!!
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return null

}


fun readThemeFromAssets(context: Context) {
    val defaultThemeFileName = "defaultTheme.json"
    // 先获取默认的主题
    val themeColor: ThemeColor? = parseThemeFromJson(context, "theme/${defaultThemeFileName}")
    themeColor?.let {
        ThemeColors.put(it.seedColor, it)
    }
    context.assets.list("theme")?.forEach {
        if (it.endsWith("json") && it != defaultThemeFileName) {
            try {
                val themeColor: ThemeColor? = parseThemeFromJson(context, "theme/${it}")
                themeColor?.let {
                    ThemeColors.put(it.seedColor, it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}

fun toColor(str: String): Color {
    return Color(android.graphics.Color.parseColor(str))
}

fun toCamelCase(str: String): String {
    val result = StringBuilder()
    var capitalizeNext = false
    for (char in str) {
        if (char == '_' || char == '-') {
            capitalizeNext = true
        } else if (capitalizeNext) {
            result.append(char.toUpperCase())
            capitalizeNext = false
        } else {
            result.append(char)
        }
    }
    return result.toString()
}