package com.example.rynnarriola.newsapp.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.rynnarriola.newsapp.base.ShowError
import com.example.rynnarriola.newsapp.base.ShowLoading
import com.example.rynnarriola.newsapp.data.model.LanguageSource
import com.example.rynnarriola.newsapp.util.UiState

@Composable
fun LanguageNewsScreen(uiState: UiState<List<LanguageSource>>, onNewsClick: (url: String) -> Unit) {
    when (uiState) {
        is UiState.Success -> {
            LanguageArticleList(uiState.data, onNewsClick)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError(uiState.message)
        }
    }
}

@Composable
fun LanguageArticleList(articles: List<LanguageSource>, onNewsClick: (url: String) -> Unit) {
    LazyColumn {
        items(articles, key = { article -> article.url }) { article ->
            LanguageArticle(article, onNewsClick)
            Divider(color = Color.Gray, thickness = 1.dp)
        }
    }
}

@Composable
fun LanguageArticle(article: LanguageSource, onNewsClick: (url: String) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            if (article.url.isNotEmpty()) {
                onNewsClick(article.url)
            }
        }) {
        TitleText(article.name)
        DescriptionText(article.description)
        LanguageSourceText(article.url)
    }
}

@Composable
fun LanguageSourceText(source: String) {
    Text(
        text = source,
        style = MaterialTheme.typography.titleSmall,
        color = Color.Blue,
        maxLines = 1,
        modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 8.dp)
    )
}