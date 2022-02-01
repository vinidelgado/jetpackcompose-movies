package com.vini.movies.presentation.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vini.movies.domain.model.Movie
import com.vini.movies.domain.use_cases.UseCases
import com.vini.movies.util.Constants.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedMovie: MutableStateFlow<Movie?> = MutableStateFlow(null)
    val selectedMovie: StateFlow<Movie?> = _selectedMovie

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val movieId = savedStateHandle.get<Int>(DETAILS_ARGUMENT_KEY)
            _selectedMovie.value = movieId?.let { useCases.getSelectedMovieUseCase(movieId = movieId) }
        }
    }
}