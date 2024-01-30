package com.example.rynnarriola.newsapp.data.repository

import com.example.rynnarriola.newsapp.data.api.NetworkService
import com.example.rynnarriola.newsapp.data.local.entity.Article
import com.example.rynnarriola.newsapp.data.model.Country
import com.example.rynnarriola.newsapp.data.model.Language
import com.example.rynnarriola.newsapp.data.model.LanguageSource
import com.example.rynnarriola.newsapp.data.model.toArticleEntity
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
            it.articles.map { apiArticle ->  apiArticle.toArticleEntity() }
        }
    }

    fun getNewsSource(source: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getNewsSource(source))
        }.map {
            it.articles.map { apiArticle ->  apiArticle.toArticleEntity() }
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
            it.articles.map { apiArticle ->  apiArticle.toArticleEntity() }
        }
    }

    fun getCountries(): Flow<List<Country>> {
        return flow {
            emit(Constants.COUNTRIES)
        }
    }

    fun getLanguages(): Flow<List<Language>> {
        return flow {
            emit(Constants.LANGUAGES)
        }
    }

}