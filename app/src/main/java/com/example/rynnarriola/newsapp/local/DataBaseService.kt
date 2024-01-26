package com.example.rynnarriola.newsapp.local

import com.example.rynnarriola.newsapp.local.entity.Article
import kotlinx.coroutines.flow.Flow

interface DatabaseService {

    fun getArticles(): Flow<List<Article>>

    fun deleteAllAndInsertAll(articles: List<Article>)

}