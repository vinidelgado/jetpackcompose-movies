package com.vini.movies.presentation.screens.home

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vini.movies.R
import com.vini.movies.ui.theme.homeScreenTopAppBarBackgroundColor
import com.vini.movies.ui.theme.homeScreenTopAppBarContentColor

@Composable
fun HomeTopAppBar(onSearchClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "Movies",
                color = MaterialTheme.colors.homeScreenTopAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.homeScreenTopAppBarBackgroundColor,
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(id = R.string.topbar_icon_speech),
                    tint = MaterialTheme.colors.homeScreenTopAppBarContentColor
                )
            }
        }
    )
}

@Composable
@Preview
fun HomeTopAppBarPreview() {
    HomeTopAppBar(onSearchClicked = {})
}