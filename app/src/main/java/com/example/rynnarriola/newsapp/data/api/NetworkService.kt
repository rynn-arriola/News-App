package com.example.rynnarriola.newsapp.data.api

import com.example.rynnarriola.newsapp.data.model.LanguageNewsResponse
import com.example.rynnarriola.newsapp.data.model.TopHeadlinesResponse
import com.example.rynnarriola.newsapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NetworkService {

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
    }

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlinesResponse

    @GET("top-headlines")
    suspend fun getNewsSource(@Query("sources") source: String): TopHeadlinesResponse

    @GET("top-headlines/sources")
    suspend fun getNewsByLanguage(@Query("language") language: String): LanguageNewsResponse

    @GET("everything")
    suspend fun getSearchNews(@Query("q") query: String): TopHeadlinesResponse

}