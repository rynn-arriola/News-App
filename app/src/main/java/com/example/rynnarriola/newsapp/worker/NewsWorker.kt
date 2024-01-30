package com.example.rynnarriola.newsapp.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.rynnarriola.newsapp.data.api.NetworkService
import com.example.rynnarriola.newsapp.data.model.toArticleEntity
import com.example.rynnarriola.newsapp.data.local.DatabaseService
import com.example.rynnarriola.newsapp.util.Constants.COUNTRY
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class NewsWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParameters: WorkerParameters,
    private val database: DatabaseService,
    private val network: NetworkService
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        return try {
            val articles = network.getTopHeadlines(COUNTRY).articles.map { it.toArticleEntity() }
            database.deleteAllAndInsertAll(articles)
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}