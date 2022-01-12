package com.vini.movies.presentation.screens.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.vini.movies.presentation.screens.common.ListContent

@ExperimentalCoilApi
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(navController: NavHostController, homeViewModel: HomeViewModel = hiltViewModel()) {

    val allMovies = homeViewModel.getAllMovies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopAppBar(onSearchClicked = {

            })
        },
        content = {
            ListContent(
                movies = allMovies,
                navController = navController
            )
        }
    )
}