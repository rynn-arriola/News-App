package com.example.rynnarriola.newsapp.ui.base

sealed class Screen(val route : String) {

    object DashBoardScreen: Screen("dashboard_screen")
    object TopHeadLinesScreen: Screen("top_head_lines_screen")
    object OfflineScreen: Screen("off_line_screen")
    object PagingScreen: Screen("paging_screen")
    object NewsSourceScreen: Screen("news_source_screen")
    object CountriesScreen: Screen("countries_screen")
    object LanguagesScreen: Screen("language_screen")
    object SearchScreen: Screen("search_screen")
    object CountriesNewsScreen: Screen("countries_news_screen")
    object LanguageNewsScreen: Screen("language_news_screen")
    object DirectSourceScreen: Screen("direct_source_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {arg->
                append("/$arg")
            }
        }
    }
}