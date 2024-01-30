package com.example.rynnarriola.newsapp.ui.newssource

import androidx.lifecycle.viewModelScope
import com.example.rynnarriola.newsapp.ui.base.BaseViewModel
import com.example.rynnarriola.newsapp.data.local.entity.Article
import com.example.rynnarriola.newsapp.data.repository.NewsRepo
import com.example.rynnarriola.newsapp.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(
    private val newsRepo: NewsRepo
): BaseViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState


    fun fetchNews(source: String) {
        viewModelScope.launch {
            newsRepo.getNewsSource(source)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}