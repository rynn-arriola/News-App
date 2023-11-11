package com.example.rynnarriola.newsapp.di.modules

import com.example.rynnarriola.newsapp.adapter.LanguageNewsAdapter
import com.example.rynnarriola.newsapp.adapter.TopHeadLinesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
class FragmentModule {

    @FragmentScoped
    @Provides
    fun provideTopHeadlineAdapter() = TopHeadLinesAdapter(ArrayList())

    @FragmentScoped
    @Provides
    fun provideLanguageNewsAdapter() = LanguageNewsAdapter(ArrayList())
}