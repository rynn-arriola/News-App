package com.example.rynnarriola.newsapp.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rynnarriola.newsapp.ui.fragments.NewsSourcesFragmentDirections


@Composable
fun NewsSourcesScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ButtonComponent(text = "Bloomberg") {
            val action = NewsSourcesFragmentDirections
                .actionNewsSourcesFragmentToDirectSourceFragment(newsSource = "bloomberg")
            navController.navigate(action)
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonComponent(text = "Wall Street Journal") {
            val action = NewsSourcesFragmentDirections
                .actionNewsSourcesFragmentToDirectSourceFragment(newsSource = "the-wall-street-journal")
            navController.navigate(action)
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonComponent(text = "NBC News") {
            val action = NewsSourcesFragmentDirections
                .actionNewsSourcesFragmentToDirectSourceFragment(newsSource = "nbc-news")
            navController.navigate(action)
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonComponent(text = "CNN") {
            val action = NewsSourcesFragmentDirections
                .actionNewsSourcesFragmentToDirectSourceFragment(newsSource = "cnn")
            navController.navigate(action)
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonComponent(text = "USA Today") {
            val action = NewsSourcesFragmentDirections
                .actionNewsSourcesFragmentToDirectSourceFragment(newsSource = "usa-today")
            navController.navigate(action)
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonComponent(text = "Reuters") {
            val action = NewsSourcesFragmentDirections
                .actionNewsSourcesFragmentToDirectSourceFragment(newsSource = "reuters")
            navController.navigate(action)
        }
    }
}




