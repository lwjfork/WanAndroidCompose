package com.wan.android.compose

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.PropertySpec
import java.io.File
import java.io.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * @Description:
 * @Date: 2024/9/5 21:25
 * @author:  liuwenjie09
 * @version: 1.0
 */


class ThemeInfo : Serializable {
    var seed: String? = ""
        get() = "0xFF${field!!.substring(1)}"
    var schemes: Schemes? = null
    var palettes: Palettes? = null

    class Schemes : Serializable {
        var light: Map<String, String> = mutableMapOf()
        var dark: Map<String, String> = mutableMapOf()
        fun getResultArray(): Map<String, Map<String, String>> {
            val result: MutableMap<String, Map<String, String>> = mutableMapOf()
            val lightResult: MutableMap<String, String> = mutableMapOf()
            val darkResult: MutableMap<String, String> = mutableMapOf()
            light.forEach { t, u ->
                lightResult.put(t, "0xFF${u.substring(1)}")
            }
            dark.forEach { t, u ->
                darkResult.put(t, "0xFF${u.substring(1)}")
            }
            result["light"] = lightResult
            result["dark"] = darkResult
            return result
        }
    }

    class Palettes : Serializable {
        var primary: Map<String, String> = mutableMapOf()
        var secondary: Map<String, String> = mutableMapOf()
        var tertiary: Map<String, String> = mutableMapOf()
        var neutral: Map<String, String> = mutableMapOf()

        @SerializedName("neutral-variant")
        var neutralVariant: Map<String, String> = mutableMapOf()


        fun getResultArray(): Map<String, String> {
            val result: MutableMap<String, String> = mutableMapOf()
            primary.forEach { t, u ->
                result.put("primary${t}", "0xFF${u.substring(1)}")
            }
            secondary.forEach { t, u ->
                result.put("secondary${t}", "0xFF${u.substring(1)}")
            }
            tertiary.forEach { t, u ->
                result.put("tertiary${t}", "0xFF${u.substring(1)}")
            }
            neutral.forEach { t, u ->
                result.put("neutral${t}", "0xFF${u.substring(1)}")
            }
            neutralVariant.forEach { t, u ->
                result.put("neutralVariant${t}", "0xFF${u.substring(1)}")
            }
            return result
        }
    }
}

val ColorScheme = ClassName("androidx.compose.material3", "ColorScheme")
val Color = ClassName("androidx.compose.ui.graphics", "Color")
val AppThemeColor = ClassName("com.wan.android.compose.ui.theme", "AppThemeColor")
val ColorPalette = ClassName("com.wan.android.compose.ui.theme", "ColorPalette")
val CustomColorScheme = ClassName("com.wan.android.compose.ui.theme", "CustomColorScheme")


fun readFile(): Array<out File> {
    val dir = File(System.getProperty("user.dir") + "/app/src/main/assets/theme")
    return dir.listFiles().orEmpty()
}

fun main() {
    val targetDir = System.getProperty("user.dir") + "/app/src/androidTest/java"
    readFile().forEach {
        val themeName = it.name.replace(".json", "").capitalize()
        val file = File(targetDir)
        val builder: FileSpec.Builder =
            FileSpec.builder("com.wan.android.compose.themes", themeName)
        val themeJson = it.readText()
        val jsonObject = Gson().fromJson(themeJson, ThemeInfo::class.java)


        builder.addFileComment("@Description:\n")
        builder.addFileComment(
            "@Date: ${
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"))
            }\n"
        )
        builder.addFileComment("@author:  liuwenjie09\n")
        builder.addFileComment("@version: 1.0\n")


        builder.addProperty(buildColorPalette(themeName,jsonObject))
        builder.addProperty(
            buildThemeItem(
                themeName,
                jsonObject.schemes!!.getResultArray()["light"]!!, "light", jsonObject.seed!!
            )
        )
        builder.addProperty(
            buildThemeItem(
                themeName,
                jsonObject.schemes!!.getResultArray()["dark"]!!, "dark", jsonObject.seed!!
            )
        )
        builder.build().writeTo(file)
    }
}

fun buildColorPalette(themeName:String,themeInfo: ThemeInfo): PropertySpec {
    val ColorPaletteCodeBlock = CodeBlock.builder().add("%T(\n", ColorPalette)
    themeInfo.palettes?.apply {
        getResultArray().forEach { t, u ->
            ColorPaletteCodeBlock.add("    ${t} = %T(${u}),\n", Color)
        }
    }
    ColorPaletteCodeBlock.add(")")
    val ColorPaletteProperty =
        PropertySpec.builder("${themeName}ColorPalette", ColorPalette).addKdoc("颜色模版")
            .initializer(ColorPaletteCodeBlock.build())

    return ColorPaletteProperty.build()

}


fun buildThemeItem(
    themeName: String, colors: Map<String, String>, darkModeName: String, seedColor: String
): PropertySpec {
    val builder = CodeBlock.Builder()
    builder.add("%T(\n", AppThemeColor)
    builder.add("isLight = ${darkModeName.toUpperCase() == "LIGHT"},\n")
    builder.add("seedColor = %T($seedColor),\n", Color)
    builder.add("colorPalette = ${themeName}ColorPalette,\n")
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

    materialColorName.let {
        val materialColorCodeBlock = CodeBlock.builder()
        materialColorCodeBlock.add("colorScheme =  %T(\n", ColorScheme)
        it.forEach {
            materialColorCodeBlock.add("    ${it} = %T(${colors[it]}),\n", Color)
        }
        materialColorCodeBlock.add("),\n")
        builder.add(materialColorCodeBlock.build())
    }

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

    customColorNames.let {
        val customCodeBlock = CodeBlock.builder()
        customCodeBlock.add("customColorScheme =  %T(\n", CustomColorScheme)
        it.forEach {
            customCodeBlock.add("    ${it} = %T(${colors[it]}),\n", Color)
        }
        customCodeBlock.add(")\n")
        builder.add(customCodeBlock.build())
    }
    builder.add(")")
    return PropertySpec.builder(
        "${themeName.capitalize()}${darkModeName.capitalize()}Theme", AppThemeColor
    ).addKdoc("${darkModeName.toLowerCase()} 主题").initializer(builder.build()).build()
}
