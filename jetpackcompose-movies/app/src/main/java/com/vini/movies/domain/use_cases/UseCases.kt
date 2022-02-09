package com.vini.movies.domain.use_cases

import com.vini.movies.domain.use_cases.all_movies.GetAllMoviesUseCase
import com.vini.movies.domain.use_cases.read_onboarding.ReadOnboardingUseCase
import com.vini.movies.domain.use_cases.save_genre.SaveMovieGenreUseCase
import com.vini.movies.domain.use_cases.save_onboarding.SaveOnboardingUseCase
import com.vini.movies.domain.use_cases.selected_movie.GetSelectedMovieUseCase

data class UseCases(
    val saveOnboardingUseCase: SaveOnboardingUseCase,
    val readOnboardingUseCase: ReadOnboardingUseCase,
    val getAllMoviesUseCase: GetAllMoviesUseCase,
    val getSelectedMovieUseCase: GetSelectedMovieUseCase,
    val saveMovieGenreUseCase: SaveMovieGenreUseCase,
)