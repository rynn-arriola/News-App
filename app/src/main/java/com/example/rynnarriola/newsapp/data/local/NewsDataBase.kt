package com.example.rynnarriola.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rynnarriola.newsapp.data.local.dao.ArticleDao
import com.example.rynnarriola.newsapp.data.local.entity.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class NewsDataBase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}