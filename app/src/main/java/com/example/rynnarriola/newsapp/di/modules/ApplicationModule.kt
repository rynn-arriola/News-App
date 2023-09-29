package com.example.rynnarriola.newsapp.di.modules

import android.content.Context
import com.example.rynnarriola.newsapp.NewsApplication
import com.example.rynnarriola.newsapp.data.api.NetworkService
import com.example.rynnarriola.newsapp.di.qualifiers.ApplicationContext
import com.example.rynnarriola.newsapp.di.qualifiers.BaseUrl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application : NewsApplication) {

    @ApplicationContext
    @Provides
    @Singleton
    fun provideApplication(): Context {
        return application
    }

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = NetworkService.BASE_URL

    @Provides
    @Singleton
    fun provideEmployeeApi(
    @BaseUrl baseUrl: String
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkService::class.java)
    }
}