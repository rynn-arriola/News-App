package com.example.rynnarriola.newsapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rynnarriola.newsapp.di.ViewModelFactory
import com.example.rynnarriola.newsapp.di.qualifiers.ViewModelKey
import com.example.rynnarriola.newsapp.viewmodel.SourceViewModel
import com.example.rynnarriola.newsapp.viewmodel.TopHeadLinesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TopHeadLinesViewModel::class)
    abstract fun bindTopHeadlinesViewModel(viewModel: TopHeadLinesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SourceViewModel::class)
    abstract fun bindSourceViewModel(viewModel: SourceViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}