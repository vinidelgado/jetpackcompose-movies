package com.vini.movies.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.vini.movies.domain.model.Movie
import com.vini.movies.ext.fromJson
import com.vini.movies.presentation.screens.detail.DetailScreen
import com.vini.movies.presentation.screens.home.HomeScreen
import com.vini.movies.presentation.screens.splash.SplashScreen
import com.vini.movies.presentation.screens.welcome.WelcomeScreen
import com.vini.movies.util.Constants.DETAILS_ARGUMENT_KEY

@ExperimentalCoilApi
@ExperimentalMaterial3Api
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
            })
        ) {
            DetailScreen(navController = navController)
        }
        composable(route = Screen.Search.route) {

        }
    }
}