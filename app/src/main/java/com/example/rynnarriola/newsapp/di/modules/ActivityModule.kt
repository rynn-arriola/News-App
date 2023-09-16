package com.example.rynnarriola.newsapp.di.modules

import android.app.Activity
import android.content.Context
import com.example.rynnarriola.newsapp.di.qualifiers.ActivityContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ActivityModule(private val activity : Activity) {

    @ActivityContext
    @Singleton
    @Provides
    fun provideContext(): Context{
        return activity
    }
}