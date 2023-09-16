package com.example.rynnarriola.newsapp.di.components

import com.example.rynnarriola.newsapp.di.modules.FragmentModule
import com.example.rynnarriola.newsapp.di.scopes.FragmentScope
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {

}