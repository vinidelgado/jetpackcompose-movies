package com.vini.movies.presentation.screens.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.vini.movies.R
import com.vini.movies.domain.model.OnboardingPage
import com.vini.movies.navigation.Screen
import com.vini.movies.ui.theme.Black19
import com.vini.movies.ui.theme.Black300
import com.vini.movies.ui.theme.EXTRA_LARGE_PADDING
import com.vini.movies.ui.theme.PAGING_INDICATOR
import com.vini.movies.ui.theme.PAGING_INDICATOR_SPACE
import com.vini.movies.ui.theme.SMALL_PADDING
import com.vini.movies.ui.theme.White300
import com.vini.movies.ui.theme.WhiteE5
import com.vini.movies.ui.theme.welcomeScreenActiveIndicatorColor
import com.vini.movies.ui.theme.welcomeScreenButtonBackgroundColor
import com.vini.movies.ui.theme.welcomeScreenButtonTextColor
import com.vini.movies.ui.theme.welcomeScreenDescriptionColor
import com.vini.movies.ui.theme.welcomeScreenInactiveIndicatorColor
import com.vini.movies.ui.theme.welcomeScreenTitleColor
import com.vini.movies.util.Constants.LAST_ONBOARDING_PAGE
import com.vini.movies.util.Constants.ONBOARDING_PAGE_COUNT

@ExperimentalPagerApi
@Composable
fun WelcomeScreen(
    navController: NavHostController,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    val pages = listOf(
        OnboardingPage.First,
        OnboardingPage.Second,
        OnboardingPage.Third,
    )
    val pageState = rememberPagerState()
    val background = if (isSystemInDarkTheme()) {
        WhiteE5
    } else {
        Black19
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            state = pageState,
            count = ONBOARDING_PAGE_COUNT,
            verticalAlignment = Alignment.Top
        ) { position ->
            PagerScreen(onboardingPage = pages[position])
        }
        HorizontalPagerIndicator(
            modifier = Modifier.weight(1f),
            pagerState = pageState,
            activeColor = MaterialTheme.colors.welcomeScreenActiveIndicatorColor,
            inactiveColor = MaterialTheme.colors.welcomeScreenInactiveIndicatorColor,
            indicatorWidth = PAGING_INDICATOR,
            spacing = PAGING_INDICATOR_SPACE
        )
        FinishButton(
            modifier = Modifier.weight(1f),
            pageState = pageState
        ) {
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
            welcomeViewModel.savingOnboardingScreen(completed = true)
        }
    }
}

@Composable
fun PagerScreen(onboardingPage: OnboardingPage) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.55f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = onboardingPage.image),
            contentDescription = stringResource(id = R.string.app_name)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING, vertical = SMALL_PADDING),
            text = onboardingPage.title,
            color = MaterialTheme.colors.welcomeScreenTitleColor,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING, vertical = SMALL_PADDING),
            text = onboardingPage.description,
            color = MaterialTheme.colors.welcomeScreenDescriptionColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@ExperimentalPagerApi
@Composable
fun FinishButton(
    modifier: Modifier,
    pageState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.padding(horizontal = EXTRA_LARGE_PADDING),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pageState.currentPage == LAST_ONBOARDING_PAGE
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.welcomeScreenButtonBackgroundColor,
                    contentColor = MaterialTheme.colors.welcomeScreenButtonTextColor,
                )
            ) {
                Text(text = "Finish")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FirstOnboardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onboardingPage = OnboardingPage.First)
    }
}

@Composable
@Preview(showBackground = true)
fun SecondOnboardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onboardingPage = OnboardingPage.Second)
    }
}

@Composable
@Preview(showBackground = true)
fun ThirdOnboardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onboardingPage = OnboardingPage.Third)
    }
}