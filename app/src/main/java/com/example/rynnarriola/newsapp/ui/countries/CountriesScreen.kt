package com.example.rynnarriola.newsapp.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.rynnarriola.newsapp.ui.base.NewsTopAppBar
import com.example.rynnarriola.newsapp.ui.base.Screen
import com.example.rynnarriola.newsapp.ui.base.ShowError
import com.example.rynnarriola.newsapp.ui.base.ShowLoading
import com.example.rynnarriola.newsapp.data.model.Country
import com.example.rynnarriola.newsapp.util.Constants
import com.example.rynnarriola.newsapp.util.UiState
import com.example.rynnarriola.newsapp.ui.countries.CountriesViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesScreen(viewModel: CountriesViewModel = hiltViewModel(), navController: NavController) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            NewsTopAppBar(
                title = Constants.COUNTRIES_SOURCES, showBackArrow = true
            ) { navController.popBackStack() }
        },
        content = { padding ->
            CountriesContent(
                padding = padding,
                uiState = uiState,
                navController = navController
            )
        }
    )
}

@Composable
private fun CountriesContent(
    padding: PaddingValues,
    uiState: UiState<List<Country>>,
    navController: NavController
) {
    Column(modifier = Modifier.padding(padding)) {
        when (uiState) {
            is UiState.Success -> {
                CountriesAdapter(uiState.data, navController)
            }

            is UiState.Loading -> {
                ShowLoading()
            }

            is UiState.Error -> {
                ShowError(uiState.message)
            }
        }
    }
}

@Composable
fun CountriesAdapter(countries: List<Country>, navController: NavController) {
    LazyColumn {
        items(countries) { country ->
            CountryItem(country = country, navController)
        }
    }
}

@Composable
fun CountryItem(country: Country, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate(Screen.CountriesNewsScreen.withArgs(country.code, country.country))
            }
            .background(
                MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp)
            ) // Set the background color and rounded corners
    ) {
        Text(
            text = country.country,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White, // Set the text color to white
            modifier = Modifier
                .fillMaxWidth() // Fill the maximum width of the text
                .padding(16.dp), // Add padding to center the text within the box
            textAlign = TextAlign.Center // Center the text within the box
        )
    }
}