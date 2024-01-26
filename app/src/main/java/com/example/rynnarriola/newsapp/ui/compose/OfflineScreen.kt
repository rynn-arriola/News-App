package com.example.rynnarriola.newsapp.ui.compose

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.rynnarriola.newsapp.base.NewsTopAppBar
import com.example.rynnarriola.newsapp.util.Constants
import com.example.rynnarriola.newsapp.viewmodel.OfflineArticleViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfflineLineScreen(
    navController: NavController,
    viewModel: OfflineArticleViewModel = hiltViewModel(),
    onNewsClick: (url: String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = { NewsTopAppBar(title = Constants.TOP_HEADLINES, showBackArrow = true
        ) { navController.popBackStack() }
        },
        content = { padding -> TopHeadLinesContent(padding, uiState, onNewsClick) }
    )
}