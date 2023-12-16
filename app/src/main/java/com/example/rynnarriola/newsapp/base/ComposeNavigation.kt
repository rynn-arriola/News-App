package com.example.rynnarriola.newsapp.base

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
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
import com.example.rynnarriola.newsapp.ui.compose.TopHeadLineScreen

@Composable
fun Navigation() {

    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = Screen.DashBoardScreen.route) {
        composable(route = Screen.DashBoardScreen.route) {
            DashboardScreen(navController = navController)
        }
        composable(route = Screen.TopHeadLinesScreen.route) {
            TopHeadLineScreen(
                navController = navController,
                onNewsClick = { openCustomChromeTab(context, it) })
        }
        composable(route = Screen.NewsSourceScreen.route) {
            NewsSourcesScreen(navController = navController)
        }
        composable(route = Screen.CountriesScreen.route) { entry ->
            CountriesScreen(navController = navController)
        }
        composable(route = Screen.LanguagesScreen.route) {
            LanguageScreen(navController = navController)
        }
        composable(route = Screen.SearchScreen.route) {
            SearchScreen(
                navController = navController,
                onClick = { openCustomChromeTab(context, it) })
        }
        composable(route = Screen.CountriesNewsScreen.route + "/{countryCode}/{countryName}",
            arguments = listOf(
                navArgument("countryCode") {
                    type = NavType.StringType
                    defaultValue = ""
                }, navArgument("countryName") {
                    type = NavType.StringType
                    defaultValue = ""
                })
        ) { entry ->
            CountriesNewsScreen(
                navController = navController,
                countryCode = entry.arguments?.getString("countryCode"),
                countryName = entry.arguments?.getString("countryName"),
                onNewsClick = { openCustomChromeTab(context, it) })

        }

        composable(route = Screen.LanguageNewsScreen.route + "/{languageCode}/{languageName}",
            arguments = listOf(
                navArgument("languageCode") {
                    type = NavType.StringType
                    defaultValue = ""
                }, navArgument("languageName") {
                    type = NavType.StringType
                    defaultValue = ""
                })
        ) { entry ->
            LanguageNewsScreen(
                navController = navController,
                languageCode = entry.arguments?.getString("languageCode"),
                languageName = entry.arguments?.getString("languageName"),
                onNewsClick = { openCustomChromeTab(context, it) })

        }
        composable(route = Screen.DirectSourceScreen.route + "/{newsSource}/{sourceName}",
            arguments = listOf(
                navArgument("newsSource") {
                    type = NavType.StringType
                    defaultValue = ""
                }, navArgument("sourceName") {
                    type = NavType.StringType
                    defaultValue = ""
                })
        ) { entry ->
            DirectSourceScreen(
                navController = navController,
                newsSource = entry.arguments?.getString("newsSource"),
                sourceName = entry.arguments?.getString("sourceName"),
                onNewsClick = { openCustomChromeTab(context, it) })

        }
    }

}

fun openCustomChromeTab(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopAppBar(
    title: String,
    showBackArrow: Boolean,
    onBackArrowClick: () -> Unit = {}
) {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ),
        title = { Text(text = title) },
        navigationIcon = {
            if (showBackArrow) {
                IconButton(onClick = { onBackArrowClick() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        }
    )
}
