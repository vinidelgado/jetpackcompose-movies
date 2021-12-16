package com.vini.movies.navigation

sealed class Screen(val route:String){
    object Splash:Screen("splash_screen")
    object Welcome:Screen("welcome_screen")
    object Home:Screen("home_screen")
    object Detail:Screen("detail_screen/{movieId}") {
        fun passMovieId(movieId:Int):String{
            return "detail_screen/$movieId"
        }
    }
    object Search:Screen("search_screen")
}
