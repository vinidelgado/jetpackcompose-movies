package com.vini.movies.domain.use_cases

import com.vini.movies.domain.use_cases.read_onboarding.ReadOnboardingUseCase
import com.vini.movies.domain.use_cases.save_onboarding.SaveOnboardingUseCase

data class UseCases(
    val saveOnboardingUseCase: SaveOnboardingUseCase,
    val readOnboardingUseCase: ReadOnboardingUseCase
)