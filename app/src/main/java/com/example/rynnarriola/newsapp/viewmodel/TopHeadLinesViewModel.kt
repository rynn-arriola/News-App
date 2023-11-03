package com.example.rynnarriola.newsapp.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.rynnarriola.newsapp.base.BaseViewModel
import com.example.rynnarriola.newsapp.data.model.Article
import com.example.rynnarriola.newsapp.data.repository.NewsRepo
import com.example.rynnarriola.newsapp.util.Constants.COUNTRY
import com.example.rynnarriola.newsapp.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopHeadLinesViewModel @Inject constructor(
    private val newsRepo: NewsRepo
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    init {
        fetchNews()
    }

    fun fetchNews() {
        viewModelScope.launch {
            newsRepo.getTopHeadlines(COUNTRY)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}