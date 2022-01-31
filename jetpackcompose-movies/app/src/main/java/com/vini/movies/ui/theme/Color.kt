package com.vini.movies.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White

//NewColors
val WhiteE5 = Color(0xFFE5E5E5)
val WhiteF7 = Color(0xFFF7F7F7)
val BlueOF = Color(0xFF0E2251)
val Blue17 = Color(0xFF175FC5)
val Black19 = Color(0xFF191D28)
val Black07 = Color(0xFF070408)

//OldColors
val Purple200 = Color(0xFFA970FF)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val Black700 = Color(0xFF18181A)

val Black300 = Color(0xFF1C1C1E)
val White300 = Color(0xFFFFFBFE)

val White700 = Color(0xFFFFFFFF)

val LightGray = Color(0xFFD8D8D8)

val ShimmerLightGray = Color(0xFFF1F1F1)
val ShimmerMediumGray = Color(0xFFE3E3E3)
val ShimmerDarkGray = Color(0xFF1D1D1D)

val BackgroundWhite = Color(0xFF232534)
val BackgroundBlack = Color(0xFFF3F9FF)

val Colors.backgroundColor
    @Composable
    get() = if (isLight) BackgroundWhite else BackgroundBlack

val Colors.welcomeScreenTitleColor
    @Composable
    get() = if (isLight) White else Black

val Colors.welcomeScreenDescriptionColor
    @Composable
    get() = if (isLight) White300.copy(alpha = 0.8f) else Black300.copy(alpha = 0.8f)

val Colors.welcomeScreenActiveIndicatorColor
    @Composable
    get() = if (isLight) Purple500 else Purple700

val Colors.welcomeScreenInactiveIndicatorColor
    @Composable
    get() = if (isLight) White.copy(alpha = 0.4f) else Purple700.copy(alpha = 0.4f)

val Colors.welcomeScreenButtonBackgroundColor
    @Composable
    get() = if (isLight) Purple500 else Purple700

val Colors.welcomeScreenButtonTextColor
    @Composable
    get() = if (isLight) White300 else White300

val Colors.homeScreenTopAppBarBackgroundColor
    @Composable
    get() = if (isLight) Purple500 else Black700

val Colors.homeScreenTopAppBarContentColor
    @Composable
    get() = if (isLight) White300 else White300