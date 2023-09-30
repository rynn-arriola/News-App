package com.example.rynnarriola.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rynnarriola.newsapp.data.model.LanguageSource
import com.example.rynnarriola.newsapp.data.repository.NewsRepo
import com.example.rynnarriola.newsapp.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class LanguageViewModel @Inject constructor(
    private val newsRepo: NewsRepo
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<LanguageSource>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<LanguageSource>>> = _uiState

    fun fetchNews(languageCode: String) {
        viewModelScope.launch {
            newsRepo.getNewsByLanguage(languageCode)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}