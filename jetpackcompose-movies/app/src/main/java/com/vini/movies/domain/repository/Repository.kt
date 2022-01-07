package com.vini.movies.domain.repository

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor( private val dataStore:DataStoreOperations) {

    suspend fun saveOnboardingState(completed:Boolean){
        dataStore.saveOnboardingViewState(completed = completed)
    }

    fun readOnboardingState(): Flow<Boolean> {
        return dataStore.readOnboardingViewState()
    }
}