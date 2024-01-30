package com.example.rynnarriola.newsapp.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.rynnarriola.newsapp.ui.base.NewsTopAppBar
import com.example.rynnarriola.newsapp.ui.base.ShowError
import com.example.rynnarriola.newsapp.ui.base.ShowLoading
import com.example.rynnarriola.newsapp.data.local.entity.Article
import com.example.rynnarriola.newsapp.util.UiState
import com.example.rynnarriola.newsapp.ui.countries.CountriesNewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesNewsScreen(
    navController: NavController,
    countryCode: String?,
    countryName: String?,
    viewModel: CountriesNewsViewModel = hiltViewModel(),
    onNewsClick: (url: String) -> Unit
) {
    Scaffold(
        topBar = {
            countryName?.let {
                NewsTopAppBar(
                    title = it, showBackArrow = true
                ) { navController.popBackStack() }
            }
        },
        content = { padding -> CountryNewsContent(padding, viewModel, countryCode, onNewsClick) }
    )

}

@Composable
private fun CountryNewsContent(
    padding: PaddingValues,
    viewModel: CountriesNewsViewModel = hiltViewModel(),
    countryCode: String?,
    onNewsClick: (url: String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    if (countryCode != null) {
        viewModel.fetchNews(countryCode)
    }

    Column(modifier = androidx.compose.ui.Modifier.padding(padding)) {
        when (uiState) {
            is UiState.Success -> {
                ArticleList((uiState as UiState.Success<List<Article>>).data, onNewsClick)
            }

            is UiState.Loading -> {
                ShowLoading()
            }

            is UiState.Error -> {
                ShowError((uiState as UiState.Error).message)
            }
        }
    }
}