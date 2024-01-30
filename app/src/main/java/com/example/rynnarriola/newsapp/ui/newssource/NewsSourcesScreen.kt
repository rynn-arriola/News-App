package com.example.rynnarriola.newsapp.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rynnarriola.newsapp.ui.base.NewsTopAppBar
import com.example.rynnarriola.newsapp.ui.base.Screen
import com.example.rynnarriola.newsapp.util.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsSourcesScreen(navController: NavController) {
    Scaffold(
        topBar = { NewsTopAppBar(title = Constants.NEWS_SOURCES, showBackArrow = true
        ) { navController.popBackStack() }
        },
        content = { padding -> NewsSourceContent(padding, navController) }
    )
}
@Composable
private fun NewsSourceContent(
    padding: PaddingValues,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ButtonComponent(text = "Bloomberg") {
            navController.navigate(Screen.DirectSourceScreen.withArgs("bloomberg", "Bloomberg"))
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonComponent(text = "Wall Street Journal") {
            navController.navigate(Screen.DirectSourceScreen.withArgs("the-wall-street-journal", "Wall Street Journal"))
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonComponent(text = "NBC News") {
            navController.navigate(Screen.DirectSourceScreen.withArgs("nbc-news", "NBC News"))
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonComponent(text = "CNN") {
            navController.navigate(Screen.DirectSourceScreen.withArgs("cnn", "CNN"))
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonComponent(text = "USA Today") {
            navController.navigate(Screen.DirectSourceScreen.withArgs("usa-today", "USA Today"))
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonComponent(text = "Reuters") {
            navController.navigate(Screen.DirectSourceScreen.withArgs("reuters", "Reuters"))
        }
    }
}




