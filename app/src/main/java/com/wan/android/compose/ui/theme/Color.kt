package com.wan.android.compose.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color



@Stable
class AppColors(
    isDark: Boolean, bottomTabDefaultColor: Color, bottomTabSelectedColor: Color
) {
    var isDark by mutableStateOf(isDark)
        private set
    var bottomTabDefaultColor by mutableStateOf(bottomTabDefaultColor)
        private set
    var bottomTabSelectedColor by mutableStateOf(bottomTabSelectedColor)
        private set

    fun update(colors: AppColors) {
        this.isDark = colors.isDark
        this.bottomTabDefaultColor = colors.bottomTabDefaultColor
        this.bottomTabSelectedColor = colors.bottomTabSelectedColor
    }

    fun copy() = AppColors(isDark, bottomTabDefaultColor, bottomTabSelectedColor)
}

