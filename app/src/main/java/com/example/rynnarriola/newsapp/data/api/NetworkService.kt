package com.example.rynnarriola.newsapp.data.api

import com.example.rynnarriola.newsapp.data.model.TopHeadlinesResponse
import com.example.rynnarriola.newsapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NetworkService {

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
    }

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlinesResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getBloombergNews(@Query("sources") source: String): TopHeadlinesResponse
}