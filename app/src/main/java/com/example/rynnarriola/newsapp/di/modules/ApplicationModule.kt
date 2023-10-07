package com.example.rynnarriola.newsapp.di.modules

import android.content.Context
import com.example.rynnarriola.newsapp.NewsApplication
import com.example.rynnarriola.newsapp.data.api.NetworkService
import com.example.rynnarriola.newsapp.data.api.SupportInterceptor
import com.example.rynnarriola.newsapp.di.qualifiers.ApplicationContext
import com.example.rynnarriola.newsapp.di.qualifiers.BaseUrl
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApplicationModule(private val application : NewsApplication) {

    @ApplicationContext
    @Provides
    @Singleton
    fun provideApplication(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY // Logs both headers and body
        return SupportInterceptor() // Return a SupportInterceptor instance
    }

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = NetworkService.BASE_URL

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