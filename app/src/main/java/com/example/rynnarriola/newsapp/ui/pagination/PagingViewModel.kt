package com.example.rynnarriola.newsapp.ui.pagination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.rynnarriola.newsapp.data.local.entity.Article
import com.example.rynnarriola.newsapp.data.repository.PagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PagingViewModel @Inject constructor(private val pagingRepo: PagingRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<PagingData<Article>>(value = PagingData.empty())

    val uiState: StateFlow<PagingData<Article>> = _uiState

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            pagingRepo.getTopHeadlines()
                .collect {
                    _uiState.value = it
                }
        }
    }
}