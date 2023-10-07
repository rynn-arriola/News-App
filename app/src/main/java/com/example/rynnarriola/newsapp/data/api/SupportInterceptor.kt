    package com.example.rynnarriola.newsapp.data.api

import com.example.rynnarriola.newsapp.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

class SupportInterceptor: Interceptor {
    private val apiKey = Constants.API_KEY

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("X-Api-Key", apiKey)
            .build()
        return chain.proceed(newRequest)
    }
}