package com.vini.movies.domain.use_cases.save_onboarding

import com.vini.movies.data.repository.Repository

class SaveOnboardingUseCase(private val repository: Repository) {
    suspend operator fun invoke(completed:Boolean){
        repository.saveOnboardingState(completed = completed)
    }
}