package com.example.rynnarriola.newsapp.base

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rynnarriola.newsapp.ui.compose.CountriesNewsScreen
import com.example.rynnarriola.newsapp.ui.compose.CountriesScreen
import com.example.rynnarriola.newsapp.ui.compose.DashboardScreen
import com.example.rynnarriola.newsapp.ui.compose.DirectSourceScreen
import com.example.rynnarriola.newsapp.ui.compose.LanguageNewsScreen
import com.example.rynnarriola.newsapp.ui.compose.LanguageScreen
import com.example.rynnarriola.newsapp.ui.compose.NewsSourcesScreen
import com.example.rynnarriola.newsapp.ui.compose.SearchScreen
import com.example.rynnarriola.newsapp.ui.compose.TopHeadlineScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = Screen.DashBoardScreen.route ) {
        composable(route = Screen.DashBoardScreen.route) {
            DashboardScreen(navController = navController)
        }
        composable(route = Screen.TopHeadLinesScreen.route) {
            TopHeadlineScreen(onNewsClick = { openCustomChromeTab(context, it)})
        }
        composable(route = Screen.NewsSourceScreen.route) {
            NewsSourcesScreen(navController = navController)
        }
        composable(route = Screen.CountriesScreen.route) { entry ->
            CountriesScreen(navController = navController)
        }
        composable(route = Screen.LanguagesScreen.route) {
            LanguageScreen(navController = navController )
        }
        composable(route = Screen.SearchScreen.route) {
            SearchScreen(onClick = { openCustomChromeTab(context, it)})
        }
        composable(route = Screen.CountriesNewsScreen.route + "/{countryCode}",
            arguments = listOf(navArgument("countryCode") {
                type = NavType.StringType
                defaultValue = ""
            })
        ) { entry ->
            CountriesNewsScreen(
                countryCode = entry.arguments?.getString("countryCode"),
                onNewsClick = { openCustomChromeTab(context, it) })

        }

        composable(route = Screen.LanguageNewsScreen.route + "/{languageCode}",
            arguments = listOf(navArgument("languageCode") {
                type = NavType.StringType
                defaultValue = ""
            })
        ) { entry ->
            LanguageNewsScreen(
                languageCode = entry.arguments?.getString("languageCode"),
                onNewsClick = { openCustomChromeTab(context, it) })

        }
        composable(route = Screen.DirectSourceScreen.route + "/{newsSource}",
            arguments = listOf(navArgument("newsSource") {
                type = NavType.StringType
                defaultValue = ""
            })
        ) { entry ->
            DirectSourceScreen(
                newsSource = entry.arguments?.getString("newsSource"),
                onNewsClick = { openCustomChromeTab(context, it) })

        }
    }

}
fun openCustomChromeTab(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}
