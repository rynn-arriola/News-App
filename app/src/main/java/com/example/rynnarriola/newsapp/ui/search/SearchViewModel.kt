package com.example.rynnarriola.newsapp.ui.search

import androidx.lifecycle.viewModelScope
import com.example.rynnarriola.newsapp.ui.base.BaseViewModel
import com.example.rynnarriola.newsapp.data.local.entity.Article
import com.example.rynnarriola.newsapp.data.repository.NewsRepo
import com.example.rynnarriola.newsapp.util.Constants.DEBOUNCE_TIMEOUT
import com.example.rynnarriola.newsapp.util.Constants.MIN_SEARCH_CHAR
import com.example.rynnarriola.newsapp.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsRepo: NewsRepo
) : BaseViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Success(emptyList()))

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    private val query = MutableStateFlow("")

    init {
        fetchNews()
    }

    fun searchNews(searchQuery: String) {
        query.value = searchQuery
    }

    fun fetchNews() {

        viewModelScope.launch {
            query.debounce(DEBOUNCE_TIMEOUT)
                .filter {
                    if (it.isNotEmpty() && it.length >= MIN_SEARCH_CHAR) {
                        return@filter true
                    } else {
                        _uiState.value = UiState.Success(emptyList())
                        return@filter false
                    }
                }
                .distinctUntilChanged()
                .flatMapLatest {
                    _uiState.value = UiState.Loading
                    return@flatMapLatest newsRepo.getSearchNews(it)
                        .catch { e ->
                            // handle error properly
                            _uiState.value = UiState.Error(e.toString())
                        }
                }
                .flowOn(Dispatchers.IO)
                .collect {
                    // handle response and empty response properly
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}