package com.vini.movies.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vini.movies.domain.model.Genre
import com.vini.movies.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {
    private val _onboardingCompleted = MutableStateFlow(false)
    val onboardingCompleted: StateFlow<Boolean> = _onboardingCompleted

    init {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.saveMovieGenreUseCase(
                listOf(
                    Genre(id = 28, name = "Action"),
                    Genre(id = 12, name = "Adventure"),
                    Genre(id = 16, name = "Animation"),
                    Genre(id = 35, name = "Comedy"),
                    Genre(id = 80, name = "Crime"),
                    Genre(id = 99, name = "Documentary"),
                    Genre(id = 18, name = "Drama"),
                    Genre(id = 10751, name = "Family"),
                    Genre(id = 14, name = "Fantasy"),
                    Genre(id = 36, name = "History"),
                    Genre(id = 27, name = "Horror"),
                    Genre(id = 10402, name = "Music"),
                    Genre(id = 9648, name = "Mystery"),
                    Genre(id = 10749, name = "Romance"),
                    Genre(id = 878, name = "Science Fiction"),
                    Genre(id = 10770, name = "TV Movie"),
                    Genre(id = 53, name = "Thriller"),
                    Genre(id = 10752, name = "War"),
                    Genre(id = 37, name = "Western"),
                )
            )
            _onboardingCompleted.value =
                useCases.readOnboardingUseCase().stateIn(viewModelScope).value
        }
    }
}