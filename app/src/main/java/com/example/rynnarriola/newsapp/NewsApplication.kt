package com.example.rynnarriola.newsapp

import android.app.Application
import com.example.rynnarriola.newsapp.di.components.ApplicationComponent
import com.example.rynnarriola.newsapp.di.components.DaggerApplicationComponent
import com.example.rynnarriola.newsapp.di.modules.ApplicationModule

class NewsApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        getDependencies()
    }

    private fun getDependencies() {

        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)

    }

}