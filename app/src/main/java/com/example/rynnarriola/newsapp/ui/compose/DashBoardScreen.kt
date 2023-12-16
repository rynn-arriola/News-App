package com.example.rynnarriola.newsapp.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rynnarriola.newsapp.base.Screen


@Composable
fun DashboardScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ButtonComponent(text = "Top Headlines") {
            navController.navigate(Screen.TopHeadLinesScreen.route)
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonComponent(text = "News Sources") {
            navController.navigate(Screen.NewsSourceScreen.route)
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonComponent(text = "Countries") {
            navController.navigate(Screen.CountriesScreen.route)
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonComponent(text = "Languages") {
            navController.navigate(Screen.LanguagesScreen.route)
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonComponent(text = "Search") {
            navController.navigate(Screen.SearchScreen.route)
        }
    }
}

@Composable
fun ButtonComponent(text: String, onClick: () ->Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.primary
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
    }
}



