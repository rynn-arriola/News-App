package com.example.rynnarriola.newsapp.di.components

import com.example.rynnarriola.newsapp.NewsApplication
import com.example.rynnarriola.newsapp.di.modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: NewsApplication)

}