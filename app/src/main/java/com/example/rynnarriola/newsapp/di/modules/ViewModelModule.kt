package com.example.rynnarriola.newsapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rynnarriola.newsapp.di.ViewModelFactory
import com.example.rynnarriola.newsapp.di.qualifiers.ViewModelKey
import com.example.rynnarriola.newsapp.viewmodel.CountriesViewModel
import com.example.rynnarriola.newsapp.viewmodel.LanguageViewModel
import com.example.rynnarriola.newsapp.viewmodel.SearchViewModel
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
    @IntoMap
    @ViewModelKey(CountriesViewModel::class)
    abstract fun bindSCountriesViewModel(viewModel: CountriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LanguageViewModel::class)
    abstract fun bindsLanguageViewModel(viewModel: LanguageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindsSearchViewModel(viewModel: SearchViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}