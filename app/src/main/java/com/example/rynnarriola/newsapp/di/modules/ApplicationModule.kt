package com.example.rynnarriola.newsapp.di.modules

import com.example.rynnarriola.newsapp.data.api.ApiKeyInterceptor
import com.example.rynnarriola.newsapp.data.api.NetworkService
import com.example.rynnarriola.newsapp.di.qualifiers.ApiKey
import com.example.rynnarriola.newsapp.di.qualifiers.BaseUrl
import com.example.rynnarriola.newsapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideInterceptor(@ApiKey apiKey: String): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY // Logs both headers and body
        return ApiKeyInterceptor(apiKey) // Return a SupportInterceptor instance
    }

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = NetworkService.BASE_URL

    @ApiKey
    @Provides
    fun provideApiKey(): String = Constants.API_KEY

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkService(
    @BaseUrl baseUrl: String,
    okHttpClient: OkHttpClient
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkService::class.java)
    }
}