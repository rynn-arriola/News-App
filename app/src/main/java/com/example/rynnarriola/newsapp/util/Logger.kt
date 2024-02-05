package com.example.rynnarriola.newsapp.util

import android.util.Log

interface Logger {

    fun log(tag: String, message: String)
}

class NewsLogger: Logger {
    override fun log(tag: String, message: String) {
        Log.d(tag, message)
    }
}