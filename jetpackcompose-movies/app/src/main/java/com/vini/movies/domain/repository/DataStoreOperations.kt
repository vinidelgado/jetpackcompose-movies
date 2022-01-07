package com.vini.movies.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun saveOnboardingViewState(completed: Boolean)
    fun readOnboardingViewState(): Flow<Boolean>
}