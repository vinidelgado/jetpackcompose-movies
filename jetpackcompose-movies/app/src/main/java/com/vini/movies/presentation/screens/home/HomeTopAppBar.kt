package com.vini.movies.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vini.movies.R
import com.vini.movies.ui.theme.Blue17
import com.vini.movies.ui.theme.BlueOF
import com.vini.movies.ui.theme.WhiteE5
import com.vini.movies.ui.theme.homeScreenTopAppBarBackgroundColor
import com.vini.movies.ui.theme.homeScreenTopAppBarContentColor

@Composable
fun HomeTopAppBar(onSearchClicked: () -> Unit) {
    val background = Brush.horizontalGradient(listOf(BlueOF, Blue17))
    TopAppBar(
        modifier = Modifier.background(background),
        title = {
            Text(
                text = "Movies",
                color = MaterialTheme.colors.homeScreenTopAppBarContentColor
            )
        },
        backgroundColor = Color.Transparent,
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.topbar_icon_speech),
                    tint = MaterialTheme.colors.homeScreenTopAppBarContentColor
                )
            }
        },
        elevation = 0.dp
    )
}

@Composable
@Preview
fun HomeTopAppBarPreview() {
    HomeTopAppBar(onSearchClicked = {})
}