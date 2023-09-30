package com.example.rynnarriola.newsapp.di.modules

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.rynnarriola.newsapp.adapter.LanguageNewsAdapter
import com.example.rynnarriola.newsapp.adapter.TopHeadLinesAdapter
import com.example.rynnarriola.newsapp.di.qualifiers.ActivityContext
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: Fragment) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return fragment.requireContext()
    }

    @Provides
    fun provideTopHeadlineAdapter() = TopHeadLinesAdapter(ArrayList())

    @Provides
    fun provideLanguageNewsAdapter() = LanguageNewsAdapter(ArrayList())
}