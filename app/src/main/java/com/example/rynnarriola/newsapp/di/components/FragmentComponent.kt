package com.example.rynnarriola.newsapp.di.components

import com.example.rynnarriola.newsapp.di.modules.FragmentModule
import com.example.rynnarriola.newsapp.di.modules.ViewModelModule
import com.example.rynnarriola.newsapp.di.scopes.FragmentScope
import com.example.rynnarriola.newsapp.ui.fragments.CountriesFragment
import com.example.rynnarriola.newsapp.ui.fragments.CountriesNewsFragment
import com.example.rynnarriola.newsapp.ui.fragments.DirectSourceFragment
import com.example.rynnarriola.newsapp.ui.fragments.LanguageNewsFragment
import com.example.rynnarriola.newsapp.ui.fragments.LanguagesFragment
import com.example.rynnarriola.newsapp.ui.fragments.SearchFragment
import com.example.rynnarriola.newsapp.ui.fragments.TopHeadLinesFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class, ViewModelModule::class]
)
interface FragmentComponent {

    fun inject(fragment: TopHeadLinesFragment)

    fun inject(fragment: DirectSourceFragment)

    fun inject(fragment: CountriesFragment)

    fun inject(fragment: CountriesNewsFragment)

    fun inject(fragment: LanguagesFragment)

    fun inject(fragment: LanguageNewsFragment)

    fun inject(fragment: SearchFragment)
}