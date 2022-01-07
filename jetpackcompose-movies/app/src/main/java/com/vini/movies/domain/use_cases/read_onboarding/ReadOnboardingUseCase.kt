package com.vini.movies.domain.use_cases.read_onboarding

import com.vini.movies.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnboardingUseCase(private val repository: Repository) {
    operator fun invoke(): Flow<Boolean> {
        return repository.readOnboardingState()
    }
}