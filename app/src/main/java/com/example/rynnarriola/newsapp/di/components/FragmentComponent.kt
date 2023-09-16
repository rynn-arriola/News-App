package com.example.rynnarriola.newsapp.di.components

import com.example.rynnarriola.newsapp.di.modules.FragmentModule
import com.example.rynnarriola.newsapp.di.modules.ViewModelModule
import com.example.rynnarriola.newsapp.di.scopes.FragmentScope
import com.example.rynnarriola.newsapp.ui.fragments.TopHeadLinesFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class, ViewModelModule::class]
)
interface FragmentComponent {
    fun inject(fragment: TopHeadLinesFragment)
}