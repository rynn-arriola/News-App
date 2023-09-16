package com.example.rynnarriola.newsapp.di.modules

import android.content.Context
import com.example.rynnarriola.newsapp.NewsApplication
import com.example.rynnarriola.newsapp.di.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application : NewsApplication) {

    @ApplicationContext
    @Provides
    @Singleton
    fun provideApplication(): Context {
        return application
    }
}