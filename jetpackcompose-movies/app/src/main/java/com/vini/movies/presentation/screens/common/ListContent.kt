package com.vini.movies.presentation.screens.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.vini.movies.R
import com.vini.movies.domain.model.Movie
import com.vini.movies.navigation.Screen
import com.vini.movies.presentation.screens.components.ShimmerEffect
import com.vini.movies.ui.theme.Black19
import com.vini.movies.ui.theme.DEFAULT_PADDING
import com.vini.movies.ui.theme.EXTRA_SMALLEST_PADDING
import com.vini.movies.ui.theme.MOVIE_ITEM_HEIGHT
import com.vini.movies.ui.theme.MOVIE_ITEM_WIDTH
import com.vini.movies.ui.theme.PlayFont
import com.vini.movies.ui.theme.SMALL_PADDING
import com.vini.movies.ui.theme.WhiteE5

@ExperimentalCoilApi
@Composable
fun ListContent(
    movies: LazyPagingItems<Movie>,
    navController: NavHostController
) {
    val result = handlePagingResult(movies = movies)

    if (result) {
        LazyRow(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            horizontalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ) {
            items(
                items = movies,
                key = { movie ->
                    movie.id
                }
            ) { movie ->
                movie?.let {
                    MovieItem(movie = it, navController = navController)
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    movies: LazyPagingItems<Movie>
): Boolean {
    movies.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }
            error != null -> {
                Log.d("ListContent", "Error")
//                EmptyScreen(error = error)
                false
            }
            else -> {
                Log.d("ListContent", "True")
                true
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun MovieItem(
    movie: Movie,
    navController: NavHostController
) {
    val painter =
        rememberImagePainter(data = "https://image.tmdb.org/t/p/w200${movie.poster_path}") {
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_placeholder)
        }

    val backgroundBottom = if (isSystemInDarkTheme()) {
        Black19
    } else {
        WhiteE5
    }

    Card(
        modifier = Modifier
            .width(MOVIE_ITEM_WIDTH)
            .height(MOVIE_ITEM_HEIGHT)
            .clickable {
                navController.navigate(Screen.Detail.passMovieId(movieId = movie.id))
            },
        shape = RoundedCornerShape(DEFAULT_PADDING),
        backgroundColor = backgroundBottom
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier
                    .fillMaxHeight(0.7f)
                    .fillMaxWidth(),
                painter = painter,
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = SMALL_PADDING, bottom = SMALL_PADDING),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                FavoriteSection(movie = movie)
                MovieNameSection(movie = movie)
                movie.release_date?.let {
                    Spacer(
                        modifier = Modifier
                            .height(EXTRA_SMALLEST_PADDING)
                    )
                    ReleaseDateSection(movie = movie)
                }
            }
        }
    }
}

@Composable
fun ReleaseDateSection(movie: Movie) {
    val textColor = if (isSystemInDarkTheme()) {
        WhiteE5
    } else {
        Black19
    }
    Row(
        modifier = Modifier.padding(
            start = SMALL_PADDING,
            end = SMALL_PADDING
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .height(15.dp),
            painter = painterResource(id = R.drawable.ic_calendar_release),
            contentDescription = stringResource(R.string.app_name),
            contentScale = ContentScale.FillHeight
        )
        Spacer(
            modifier = Modifier
                .width(EXTRA_SMALLEST_PADDING)
        )
        val year = movie.release_date?.substring(0, 4) ?: ""
        val month = movie.release_date?.substring(5, 7) ?: ""
        val dateFormatted = "$year/$month"
        Text(
            text = dateFormatted,
            fontFamily = PlayFont,
            color = textColor.copy(alpha = ContentAlpha.medium),
            fontSize = MaterialTheme.typography.subtitle2.fontSize,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }


}

@Composable
fun MovieNameSection(movie: Movie) {
    val textColor = if (isSystemInDarkTheme()) {
        WhiteE5
    } else {
        Black19
    }
    Text(
        modifier = Modifier.padding(start = SMALL_PADDING, end = SMALL_PADDING),
        text = movie.title.toString(),
        fontFamily = PlayFont,
        color = textColor.copy(alpha = ContentAlpha.high),
        fontSize = MaterialTheme.typography.subtitle1.fontSize,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun FavoriteSection(movie: Movie) {
    val textColor = if (isSystemInDarkTheme()) {
        WhiteE5
    } else {
        Black19
    }
    Row(
        modifier = Modifier.padding(
            start = SMALL_PADDING,
            end = SMALL_PADDING
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .height(15.dp),
            painter = painterResource(id = R.drawable.ic_favorite),
            contentDescription = stringResource(R.string.app_name),
            contentScale = ContentScale.FillHeight
        )
        Spacer(
            modifier = Modifier
                .width(EXTRA_SMALLEST_PADDING)
        )
        val textMovie = if (movie.vote_average <= 0f) {
            stringResource(id = R.string.not_rated)
        } else {
            movie.vote_average.toString()
        }
        Text(
            text = textMovie,
            fontFamily = PlayFont,
            color = textColor.copy(alpha = ContentAlpha.medium),
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ReleaseSectionPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        ReleaseDateSection(
            Movie(
                id = 283552,
                poster_path = "/pEFRzXtLmxYNjGd0XqJDHPDFKB2.jpg",
                adult = false,
                overview = "A lighthouse keeper and his wife living off the coast of Western Australia raise a baby they rescue from an adrift rowboat.",
                release_date = "2016-09-02",
                original_title = "The Light Between Oceans",
                original_language = "en",
                title = "The Light Between Oceans",
                backdrop_path = "/2Ah63TIvVmZM3hzUwR5hXFg2LEk.jpg",
                popularity = 4.546151f,
                vote_count = 11,
                video = false,
                vote_average = 4.41f
            )
        )
    }
}


@Preview(showBackground = true)
@Composable
fun MovieItemPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        MovieItem(
            Movie(
                id = 283552,
                poster_path = "/pEFRzXtLmxYNjGd0XqJDHPDFKB2.jpg",
                adult = false,
                overview = "A lighthouse keeper and his wife living off the coast of Western Australia raise a baby they rescue from an adrift rowboat.",
                release_date = "2016-09-02",
                original_title = "The Light Between Oceans",
                original_language = "en",
                title = "The Light Between Oceans",
                backdrop_path = "/2Ah63TIvVmZM3hzUwR5hXFg2LEk.jpg",
                popularity = 4.546151f,
                vote_count = 11,
                video = false,
                vote_average = 4.41f
            ),
            navController = rememberNavController()
        )
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MovieItemNightPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        MovieItem(
            Movie(
                id = 283552,
                poster_path = "/pEFRzXtLmxYNjGd0XqJDHPDFKB2.jpg",
                adult = false,
                overview = "A lighthouse keeper and his wife living off the coast of Western Australia raise a baby they rescue from an adrift rowboat.",
                release_date = "2016-09-02",
                original_title = "The Light Between Oceans",
                original_language = "en",
                title = "The Light Between Oceans",
                backdrop_path = "/2Ah63TIvVmZM3hzUwR5hXFg2LEk.jpg",
                popularity = 4.546151f,
                vote_count = 11,
                video = false,
                vote_average = 4.41f
            ),
            navController = rememberNavController()
        )
    }
}
