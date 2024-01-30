package com.example.rynnarriola.newsapp.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.rynnarriola.newsapp.ui.base.NewsTopAppBar
import com.example.rynnarriola.newsapp.ui.base.ShowError
import com.example.rynnarriola.newsapp.ui.base.ShowLoading
import com.example.rynnarriola.newsapp.data.local.entity.Article
import com.example.rynnarriola.newsapp.util.UiState
import com.example.rynnarriola.newsapp.ui.newssource.SourceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DirectSourceScreen(
    navController: NavController,
    viewModel: SourceViewModel = hiltViewModel(),
    newsSource: String?,
    sourceName: String?,
    onNewsClick: (url: String) -> Unit
) {

    Scaffold(
        topBar = {
            sourceName?.let {
                NewsTopAppBar(
                    title = it, showBackArrow = true
                ) { navController.popBackStack() }
            }
        },
        content = { padding -> DirectSourceContent(padding, viewModel, newsSource, onNewsClick) }
    )

}

@Composable
private fun DirectSourceContent(
    padding: PaddingValues,
    viewModel: SourceViewModel = hiltViewModel(),
    newsSource: String?,
    onNewsClick: (url: String) -> Unit

) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        if (newsSource != null) {
            viewModel.fetchNews(newsSource)
        }
    }

    Column(modifier = Modifier.padding(padding)) {
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