package com.vini.movies.presentation.screens.detail

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.vini.movies.R
import com.vini.movies.domain.model.Movie
import com.vini.movies.ui.theme.Black07
import com.vini.movies.ui.theme.Black19
import com.vini.movies.ui.theme.LARGE_PADDING
import com.vini.movies.ui.theme.PlayFont
import com.vini.movies.ui.theme.WhiteE5
import com.vini.movies.ui.theme.WhiteF7

@ExperimentalCoilApi
@Composable
fun DetailScreen(navController: NavController,detailsViewModel: DetailsViewModel = hiltViewModel()) {
    val selectedMovie by detailsViewModel.selectedMovie.collectAsState()
    DetailContent(
        navController = navController,
        movie = selectedMovie,
    )
}