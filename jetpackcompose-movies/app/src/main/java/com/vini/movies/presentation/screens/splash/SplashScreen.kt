package com.vini.movies.presentation.screens.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.vini.movies.R
import com.vini.movies.domain.model.Movie
import com.vini.movies.ext.toJson
import com.vini.movies.navigation.Screen
import com.vini.movies.ui.theme.Black700
import com.vini.movies.ui.theme.Blue17
import com.vini.movies.ui.theme.BlueOF
import com.vini.movies.ui.theme.Purple200
import com.vini.movies.ui.theme.Purple500
import com.vini.movies.ui.theme.Purple700
import com.vini.movies.ui.theme.White700
import com.vini.movies.ui.theme.WhiteE5

@Composable
fun SplashScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {

    val onboardingCompleted by splashViewModel.onboardingCompleted.collectAsState()

    val fade = remember {
        Animatable(initialValue = 0f)
    }
    LaunchedEffect(key1 = true) {
        fade.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 550,
                delayMillis = 200,
                easing = FastOutLinearInEasing
            )
        )
        navController.popBackStack()
        if (onboardingCompleted) {
            navController.navigate(Screen.Home.route)
        } else {
            navController.navigate(Screen.Home.route)
        }
    }

    Splash(alpha = fade.value)
}

@Composable
fun Splash(alpha: Float) {
    val logoTint = if (isSystemInDarkTheme()) {
        WhiteE5
    } else {
        Blue17
    }
    val background = if (isSystemInDarkTheme()) {
        Brush.verticalGradient(listOf(BlueOF, Blue17))
    } else {
        Brush.verticalGradient(listOf(WhiteE5, WhiteE5))
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.35f)
                .alpha(alpha),
            painter = painterResource(id = R.drawable.ic_tmdb_logo),
            contentDescription = stringResource(
                R.string.app_logo
            ),
            colorFilter = ColorFilter.tint(logoTint),
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    Splash(alpha = 1f)
}

@Composable
@Preview(uiMode = UI_MODE_NIGHT_YES)
fun SplashScreenDarkPreview() {
    Splash(alpha = 1f)
}