package com.vini.movies.presentation.screens.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@ExperimentalMaterial3Api
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            HomeTopAppBar(onSearchClicked = {

            })
        }
    ) {

    }
}