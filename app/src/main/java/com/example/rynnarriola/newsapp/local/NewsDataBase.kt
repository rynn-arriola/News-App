package com.example.rynnarriola.newsapp.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rynnarriola.newsapp.local.dao.ArticleDao
import com.example.rynnarriola.newsapp.local.entity.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class NewsDataBase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}