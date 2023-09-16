package com.example.rynnarriola.newsapp.di.components

import com.example.rynnarriola.newsapp.di.modules.ActivityModule
import com.example.rynnarriola.newsapp.di.scopes.ActivityScope
import com.example.rynnarriola.newsapp.ui.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)
}