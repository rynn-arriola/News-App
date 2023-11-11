package com.example.rynnarriola.newsapp.data.repository

import com.example.rynnarriola.newsapp.data.api.NetworkService
import com.example.rynnarriola.newsapp.data.model.Article
import com.example.rynnarriola.newsapp.data.model.Country
import com.example.rynnarriola.newsapp.data.model.LanguageSource
import com.example.rynnarriola.newsapp.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NewsRepo @Inject constructor(private val networkService: NetworkService) {

    fun getTopHeadlines(country: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlines(country))
        }.map {
            it.articles
        }
    }

    fun getNewsSource(source: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getNewsSource(source))
        }.map {
            it.articles
        }
    }

    fun getNewsByLanguage(language: String): Flow<List<LanguageSource>> {
        return flow {
            emit(networkService.getNewsByLanguage(language))
        }.map {
            it.sources
        }
    }

    fun getSearchNews(query: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getSearchNews(query))
        }.map {
            it.articles
        }
    }

    fun getCountries(): Flow<List<Country>> {
        return flow {
            emit(Constants.COUNTRIES)
        }
    }

}