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
import com.example.rynnarriola.newsapp.R


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
            navController.navigate(R.id.action_dashboardFragment_to_topHeadLinesFragment)
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonComponent(text = "News Sources") {
            navController.navigate(R.id.action_dashboardFragment_to_newsSourcesFragment)
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonComponent(text = "Countries") {
            navController.navigate(R.id.action_dashboardFragment_to_countriesFragment)
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonComponent(text = "Languages") {
            navController.navigate(R.id.action_dashboardFragment_to_languagesFragment)
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonComponent(text = "Search") {
            navController.navigate(R.id.action_dashboardFragment_to_searchFragment)
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



