package com.vini.movies.presentation.screens.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi

@ExperimentalCoilApi
@Composable
fun DetailScreen(navController: NavController,detailsViewModel: DetailsViewModel = hiltViewModel()) {
    val selectedMovie by detailsViewModel.selectedMovie.collectAsState()
    val genresMovie by detailsViewModel.movieGenres.collectAsState()
    DetailContent(
        navController = navController,
        movie = selectedMovie,
        movieGenres = genresMovie,
        detailsViewModel = detailsViewModel
    )
}