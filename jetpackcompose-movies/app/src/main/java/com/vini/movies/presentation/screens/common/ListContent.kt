package com.vini.movies.presentation.screens.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.vini.movies.domain.model.Movie
import com.vini.movies.navigation.Screen
import com.vini.movies.R
import com.vini.movies.presentation.screens.components.ShimmerEffect
import com.vini.movies.ui.theme.EXTRA_SMALL_PADDING
import com.vini.movies.ui.theme.LARGE_PADDING
import com.vini.movies.ui.theme.MEDIUM_PADDING
import com.vini.movies.ui.theme.SMALL_PADDING
import com.vini.movies.ui.theme.homeScreenTopAppBarContentColor

@ExperimentalCoilApi
@Composable
fun ListContent(
    movies: LazyPagingItems<Movie>,
    navController: NavHostController
) {
    val result = handlePagingResult(movies = movies)

    if (result) {
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
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
        rememberImagePainter(data = "https://image.tmdb.org/t/p/original${movie.backdrop_path}") {
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_placeholder)
        }

    Box(
        modifier = Modifier
            .height(400.dp)
            .clickable {
                navController.navigate(Screen.Detail.passMovieId(movieId = movie.id))
            },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(shape = RoundedCornerShape(size = LARGE_PADDING)) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = stringResource(R.string.app_name),
                contentScale = ContentScale.Crop
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING), verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = movie.title,
                    color = MaterialTheme.colors.homeScreenTopAppBarContentColor,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.height(30.dp),
                        painter = painterResource(id = R.drawable.ic_favorite),
                        contentDescription = stringResource(R.string.app_name),
                        contentScale = ContentScale.FillHeight
                    )
                    Spacer(modifier = Modifier.width(EXTRA_SMALL_PADDING))
                    Text(
                        text = movie.popularity.toString(),
                        color = Color.White.copy(alpha = ContentAlpha.medium),
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieItemPreview() {
    Box() {
        MovieItem(
            Movie(1, false, "", "Uncharted", 200f, "", "Se beber não case"),
            navController = rememberNavController()
        )
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MovieItemNightPreview() {
    Box() {
        MovieItem(
            Movie(1, false, "", "Uncharted", 200f, "", "Se beber não case"),
            navController = rememberNavController()
        )
    }
}
