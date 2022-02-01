package com.vini.movies.presentation.screens.detail

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.vini.movies.R
import com.vini.movies.domain.model.Movie
import com.vini.movies.ui.theme.Black19
import com.vini.movies.ui.theme.LARGE_PADDING
import com.vini.movies.ui.theme.MEDIUM_PADDING
import com.vini.movies.ui.theme.PlayFont
import com.vini.movies.ui.theme.SMALL_PADDING
import com.vini.movies.ui.theme.WhiteE5

@ExperimentalCoilApi
@Composable
fun DetailContent(navController: NavController, movie: Movie?) {
    movie?.let {
        Column(modifier = Modifier.fillMaxSize()) {
            PosterSection(movie)
            MovieDataSection(movie)
        }
    }
}

@Composable
fun MovieDataSection(movie: Movie) {
    val textColor = if (isSystemInDarkTheme()) {
        WhiteE5
    } else {
        Black19
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        Spacer(modifier = Modifier.height(LARGE_PADDING))
        Text(
            text = stringResource(id = R.string.last_release),
            color = textColor,
            fontFamily = PlayFont,
            fontSize = MaterialTheme.typography.h6.fontSize,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(start = MEDIUM_PADDING, end = MEDIUM_PADDING)
        )
        Spacer(modifier = Modifier.height(MEDIUM_PADDING))
        Text(
            modifier = Modifier.padding(start = MEDIUM_PADDING, end = MEDIUM_PADDING),
            text = movie.overview ?: "Not overview yet",
            fontFamily = PlayFont,
            lineHeight = 20.sp, color = textColor.copy(alpha = ContentAlpha.medium),
            fontSize = MaterialTheme.typography.subtitle2.fontSize,
            maxLines = 8,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@ExperimentalCoilApi
@Composable
fun PosterSection(movie: Movie) {
//    val background = if (isSystemInDarkTheme()) {
//        Brush.verticalGradient(listOf(Color.Transparent, Black07))
//    } else {
//        Brush.verticalGradient(listOf(Color.Transparent, WhiteF7))
//    }
    val background = painterResource(id = R.drawable.ic_rect)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f),
        contentAlignment = Alignment.BottomCenter
    ) {
        val painter =
            rememberImagePainter(data = "https://image.tmdb.org/t/p/original${movie.poster_path}") {
                placeholder(R.drawable.ic_placeholder)
                error(R.drawable.ic_placeholder)
            }
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painter,
            contentDescription = stringResource(R.string.app_name),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f), contentAlignment = Alignment.BottomStart
        ) {
            val textColor = if (isSystemInDarkTheme()) {
                WhiteE5
            } else {
                Black19
            }
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = background,
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = movie.title.toString(),
                color = textColor,
                fontFamily = PlayFont,
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(LARGE_PADDING)
            )
        }
    }
}

@ExperimentalCoilApi
@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun PreviewDetailScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        DetailScreen(navController = rememberNavController())
    }
}