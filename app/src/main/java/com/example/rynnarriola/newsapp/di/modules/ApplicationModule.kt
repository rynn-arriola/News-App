package com.example.rynnarriola.newsapp.di.modules

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import com.example.rynnarriola.newsapp.data.api.ApiKeyInterceptor
import com.example.rynnarriola.newsapp.data.api.NetworkService
import com.example.rynnarriola.newsapp.di.qualifiers.ApiKey
import com.example.rynnarriola.newsapp.di.qualifiers.BaseUrl
import com.example.rynnarriola.newsapp.di.qualifiers.DatabaseName
import com.example.rynnarriola.newsapp.data.local.DatabaseService
import com.example.rynnarriola.newsapp.data.local.NewsDataBase
import com.example.rynnarriola.newsapp.data.local.NewsDataBaseService
import com.example.rynnarriola.newsapp.util.Constants
import com.example.rynnarriola.newsapp.util.DefaultDispatcherProvider
import com.example.rynnarriola.newsapp.util.DefaultNetworkHelper
import com.example.rynnarriola.newsapp.util.DispatcherProvider
import com.example.rynnarriola.newsapp.util.Logger
import com.example.rynnarriola.newsapp.util.NetworkHelper
import com.example.rynnarriola.newsapp.util.NewsLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideDispatcherProvider(): DispatcherProvider = DefaultDispatcherProvider()

    @Provides
    @Singleton
    fun provideLogger(): Logger = NewsLogger()

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return DefaultNetworkHelper(context)
    }

    @DatabaseName
    @Provides
    fun provideDatabaseName(): String = "news-database"

    @Provides
    @Singleton
    fun provideNewsDatabase(
        @ApplicationContext context: Context,
        @DatabaseName databaseName: String
    ): NewsDataBase {
        return Room.databaseBuilder(
            context,
            NewsDataBase::class.java,
            databaseName
        ).build()
    }

    @Provides
    @Singleton
    fun provideDatabaseService(newsDataBase: NewsDataBase): DatabaseService {
        return NewsDataBaseService(newsDataBase)
    }

    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
        return WorkManager.getInstance(context)
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