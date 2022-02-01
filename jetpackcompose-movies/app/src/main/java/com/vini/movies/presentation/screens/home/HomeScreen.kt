package com.vini.movies.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.vini.movies.R
import coil.annotation.ExperimentalCoilApi
import com.vini.movies.presentation.screens.common.ListContent
import com.vini.movies.ui.theme.Black07
import com.vini.movies.ui.theme.Black19
import com.vini.movies.ui.theme.MEDIUM_PADDING
import com.vini.movies.ui.theme.PlayFont
import com.vini.movies.ui.theme.SMALL_PADDING
import com.vini.movies.ui.theme.WhiteE5
import com.vini.movies.ui.theme.WhiteF7

@ExperimentalCoilApi
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(navController: NavHostController, homeViewModel: HomeViewModel = hiltViewModel()) {

    val allMovies = homeViewModel.getLatestMovies.collectAsLazyPagingItems()
    val background = if (isSystemInDarkTheme()) {
        Brush.verticalGradient(listOf(Black07, Black19))
    } else {
        Brush.verticalGradient(listOf(WhiteF7, WhiteF7))
    }
    Scaffold(
        topBar = {
            HomeTopAppBar(onSearchClicked = {

            })
        },
        content = {
            val textColor = if (isSystemInDarkTheme()) {
                WhiteE5
            } else {
                Black19
            }
            Column(
                Modifier
                    .fillMaxSize()
                    .background(background)
            ) {
                Spacer(modifier = Modifier.height(MEDIUM_PADDING))
                Text(
                    text = stringResource(id = R.string.last_release),
                    color = textColor,
                    fontFamily = PlayFont,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = SMALL_PADDING, end = SMALL_PADDING)
                )
                Spacer(modifier = Modifier.height(SMALL_PADDING))
                ListContent(
                    movies = allMovies,
                    navController = navController
                )
            }
        }
    )
}