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
import com.example.rynnarriola.newsapp.base.Screen

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
            navController.navigate(Screen.DirectSourceScreen.withArgs("bloomberg"))
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonComponent(text = "Wall Street Journal") {
            navController.navigate(Screen.DirectSourceScreen.withArgs("the-wall-street-journal"))
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonComponent(text = "NBC News") {
            navController.navigate(Screen.DirectSourceScreen.withArgs("nbc-news"))
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonComponent(text = "CNN") {
            navController.navigate(Screen.DirectSourceScreen.withArgs("cnn"))
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonComponent(text = "USA Today") {
            navController.navigate(Screen.DirectSourceScreen.withArgs("usa-today"))
        }
        Spacer(modifier = Modifier.height(16.dp))
        ButtonComponent(text = "Reuters") {
            navController.navigate(Screen.DirectSourceScreen.withArgs("reuters"))
        }
    }
}




