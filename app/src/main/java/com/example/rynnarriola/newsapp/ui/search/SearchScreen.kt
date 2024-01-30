package com.example.rynnarriola.newsapp.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.rynnarriola.newsapp.ui.base.NewsTopAppBar
import com.example.rynnarriola.newsapp.ui.base.ShowError
import com.example.rynnarriola.newsapp.ui.base.ShowLoading
import com.example.rynnarriola.newsapp.data.local.entity.Article
import com.example.rynnarriola.newsapp.ui.topheadline.ArticleList
import com.example.rynnarriola.newsapp.util.Constants
import com.example.rynnarriola.newsapp.util.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel = hiltViewModel(), onClick: (url: String) -> Unit) {
    // Observe the UI state using the provided ViewModel
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = { NewsTopAppBar(title = Constants.SEARCH_NEWS, showBackArrow = true
        ) { navController.popBackStack() }
        },
        content = { padding -> SearchContent(padding, viewModel, uiState, onClick) }
    )

}
@Composable
private fun SearchContent(
    padding: PaddingValues,
    viewModel: SearchViewModel,
    uiState: UiState<List<Article>>,
    onClick: (url: String) -> Unit
){

    // Local variable to store the current search query
    var searchQuery by remember { mutableStateOf("") }

    // Set up the Compose UI
    Column(modifier = Modifier.padding(padding)) {
        // Search bar
        SearchBar(
            searchQuery = searchQuery,
            onSearch = { newQuery ->
                searchQuery = newQuery
                viewModel.searchNews(newQuery)
            }
        )

        // Display the UI state based on the provided data
        when (uiState) {
            is UiState.Success -> {
                ArticleList(uiState.data, onClick)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(searchQuery: String, onSearch: (String) -> Unit) {
    // You can customize the appearance of the search bar as needed
    // For simplicity, a basic TextField is used here
    // Modify it based on your design requirements
    TextField(
        value = searchQuery,
        onValueChange = {
            onSearch(it)
        },
        label = { Text("Search") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}



