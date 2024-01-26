package com.example.rynnarriola.newsapp.ui

import app.cash.turbine.test
import com.example.rynnarriola.newsapp.local.entity.Article
import com.example.rynnarriola.newsapp.data.repository.NewsRepo
import com.example.rynnarriola.newsapp.util.Constants.COUNTRY
import com.example.rynnarriola.newsapp.util.DispatcherProvider
import com.example.rynnarriola.newsapp.util.UiState
import com.example.rynnarriola.newsapp.utils.TestDispatcherProvider
import com.example.rynnarriola.newsapp.viewmodel.TopHeadLinesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TopHeadlineViewModelTest {

    @Mock
    private lateinit var newsRepo: NewsRepo

    private lateinit var dispatcherProvider: DispatcherProvider

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
    }

    @Test
    fun fetchNews_whenRepositoryResponseSuccess_shouldSetSuccessUiState() {
        runTest {
            doReturn(flowOf(emptyList<Article>()))
                .`when`(newsRepo)
                .getTopHeadlines(COUNTRY)
            val viewModel = TopHeadLinesViewModel(newsRepo, dispatcherProvider)
            viewModel.uiState.test {
                assertEquals(UiState.Success(emptyList<List<Article>>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(newsRepo, times(1)).getTopHeadlines(COUNTRY)
        }
    }

    @Test
    fun fetchNews_whenRepositoryResponseError_shouldSetErrorUiState() {
        runTest {
            val errorMessage = "Error Message For You"
            doReturn(flow<List<Article>> {
                throw IllegalStateException(errorMessage)
            })
                .`when`(newsRepo)
                .getTopHeadlines(COUNTRY)

            val viewModel = TopHeadLinesViewModel(newsRepo, dispatcherProvider)
            viewModel.uiState.test {
                assertEquals(
                    UiState.Error(IllegalStateException(errorMessage).toString()),
                    awaitItem()
                )
                cancelAndIgnoreRemainingEvents()
            }
            verify(newsRepo, times(1)).getTopHeadlines(COUNTRY)
        }
    }

    @After
    fun tearDown() {
        // do something if required
    }

}