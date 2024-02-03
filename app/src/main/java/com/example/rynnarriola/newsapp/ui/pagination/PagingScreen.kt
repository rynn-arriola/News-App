package com.example.rynnarriola.newsapp.ui.pagination

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rynnarriola.newsapp.data.local.entity.Article
import com.example.rynnarriola.newsapp.ui.base.NewsTopAppBar
import com.example.rynnarriola.newsapp.ui.base.ShowError
import com.example.rynnarriola.newsapp.ui.base.ShowLoading
import com.example.rynnarriola.newsapp.ui.topheadline.Article
import com.example.rynnarriola.newsapp.util.Constants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PagingScreen(
    navController: NavController,
    viewModel: PagingViewModel = hiltViewModel(),
    onNewsClick: (url: String) -> Unit
) {
    val articles = viewModel.uiState.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            NewsTopAppBar(
                title = Constants.TOP_HEADLINES, showBackArrow = true
            ) { navController.popBackStack() }
        },
        content = { padding -> PagingContent(padding, articles, onNewsClick) }
    )
}

@Composable
fun PagingContent(
    padding: PaddingValues,
    articles: LazyPagingItems<Article>,
    onNewsClick: (url: String) -> Unit
) {
    Column(modifier = Modifier.padding(padding)) {

        PagingList(articles, onNewsClick)

        articles.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    ShowLoading()
                }

                loadState.refresh is LoadState.Error -> {
                    val error = articles.loadState.refresh as LoadState.Error
                    ShowError(error.error.localizedMessage!!)
                }

                loadState.append is LoadState.Loading -> {
                    ShowLoading()
                }

                loadState.append is LoadState.Error -> {
                    val error = articles.loadState.append as LoadState.Error
                    ShowError(error.error.localizedMessage!!)
                }
            }
        }
    }
}

@Composable
fun PagingList(articles: LazyPagingItems<Article>, onNewsClick: (url: String) -> Unit) {
    LazyColumn {
        items(articles.itemCount, key = { index -> articles[index]!!.url }) { index ->
            Article(articles[index]!!, onNewsClick)
        }
    }
}