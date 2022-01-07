package com.vini.movies.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White

val Purple200 = Color(0xFFA970FF)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val Black700 = Color(0xFF18181A)

val Black300 = Color(0xFF1C1C1E)
val White300 = Color(0xFFFFFBFE)

val White700 = Color(0xFFFFFFFF)

val LightGray = Color(0xFFD8D8D8)

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