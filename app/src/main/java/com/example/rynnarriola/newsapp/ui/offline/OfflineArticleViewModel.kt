package com.example.rynnarriola.newsapp.ui.offline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rynnarriola.newsapp.data.repository.OfflineArticleRepository
import com.example.rynnarriola.newsapp.data.local.entity.Article
import com.example.rynnarriola.newsapp.util.Constants.COUNTRY
import com.example.rynnarriola.newsapp.util.NetworkHelper
import com.example.rynnarriola.newsapp.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OfflineArticleViewModel @Inject constructor(
    networkHelper: NetworkHelper,
    private val offlineArticleRepository: OfflineArticleRepository
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    init {
        if (networkHelper.isNetworkConnected()) {
            fetchArticles()
        } else {
            fetchArticlesDirectlyFromDB()
        }
    }

    private fun fetchArticles() {
        viewModelScope.launch {
            offlineArticleRepository.getArticles(COUNTRY)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    private fun fetchArticlesDirectlyFromDB() {
        viewModelScope.launch {
            offlineArticleRepository.getArticlesDirectlyFromDB()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

}