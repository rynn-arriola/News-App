package com.example.rynnarriola.newsapp.repo

import app.cash.turbine.test
import com.example.rynnarriola.newsapp.data.api.NetworkService
import com.example.rynnarriola.newsapp.data.model.Article
import com.example.rynnarriola.newsapp.data.model.Source
import com.example.rynnarriola.newsapp.data.model.TopHeadlinesResponse
import com.example.rynnarriola.newsapp.data.repository.NewsRepo
import com.example.rynnarriola.newsapp.util.Constants.COUNTRY
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TopHeadlineRepositoryTest {

    @Mock
    private lateinit var networkService: NetworkService

    private lateinit var newsRepo: NewsRepo

    @Before
    fun setUp() {
        newsRepo = NewsRepo(networkService)
    }

    @Test
    fun getTopHeadlines_whenNetworkServiceResponseSuccess_shouldReturnSuccess() {
        runTest {
            val source = Source(id = "sourceId", name = "sourceName")
            val article = Article(
                title = "title",
                description = "description",
                url = "url",
                imageUrl = "urlToImage",
                source = source
            )

            val articles = mutableListOf<Article>()
            articles.add(article)

            val topHeadlinesResponse = TopHeadlinesResponse(
                status = "ok", totalResults = 1, articles = articles
            )

            Mockito.doReturn(topHeadlinesResponse).`when`(networkService).getTopHeadlines(COUNTRY)

            newsRepo.getTopHeadlines(COUNTRY).test {
                assertEquals(topHeadlinesResponse.articles, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }

            Mockito.verify(networkService, Mockito.times(1)).getTopHeadlines(COUNTRY)
        }
    }

    @Test
    fun getTopHeadlines_whenNetworkServiceResponseError_shouldReturnError() {
        runTest {
            val errorMessage = "Error Message For You"

            Mockito.doThrow(RuntimeException(errorMessage)).`when`(networkService).getTopHeadlines(COUNTRY)

            newsRepo.getTopHeadlines(COUNTRY).test {
                assertEquals(errorMessage, awaitError().message)
                cancelAndIgnoreRemainingEvents()
            }
            Mockito.verify(networkService, Mockito.times(1)).getTopHeadlines(COUNTRY)
        }
    }

}