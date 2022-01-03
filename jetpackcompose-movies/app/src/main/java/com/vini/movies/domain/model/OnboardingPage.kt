package com.vini.movies.domain.model

import androidx.annotation.DrawableRes
import com.vini.movies.R

sealed class OnboardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First:OnboardingPage(
        image = R.drawable.welcome,
        title = "Bem-vindo",
        description = "Obrigado por utilizar nosso app e divirta-se."
    )
    object Second:OnboardingPage(
        image = R.drawable.calendar,
        title = "Últimos lançamentos",
        description = "Saiba os últimos lançamentos."
    )
    object Third:OnboardingPage(
        image = R.drawable.favorite,
        title = "Favoritos",
        description = "Gostou dos filmes que viu, você pode favoritar."
    )
}
