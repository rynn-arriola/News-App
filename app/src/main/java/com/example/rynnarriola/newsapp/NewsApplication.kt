package com.example.rynnarriola.newsapp

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.rynnarriola.newsapp.util.Constants.WORK_NAME
import com.example.rynnarriola.newsapp.util.calculateInitialDelay
import com.example.rynnarriola.newsapp.worker.NewsWorker
import com.example.rynnarriola.newsapp.worker.WorkStatusObserver
import dagger.hilt.android.HiltAndroidApp
import java.util.Calendar
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class NewsApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var workManager: WorkManager


    override fun onCreate() {
        super.onCreate()
        observeWorkStatus()
        scheduleNewsWorker()

    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    private fun scheduleNewsWorker() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val initialDelay = Calendar.getInstance().calculateInitialDelay(targetHour = 3)

        val workRequest = OneTimeWorkRequestBuilder<NewsWorker>()
            .setConstraints(constraints)
            .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
            .addTag(WORK_NAME) //this is so we can have unique tag on debug or else ClassName will show
            .build()

        workManager.enqueueUniqueWork(
            WORK_NAME,
            ExistingWorkPolicy.KEEP,
            workRequest
        )
    }

    //created this just to check the status of workManager
    private fun observeWorkStatus() {
        val workStatusObserver = WorkStatusObserver { workInfos ->
            for (workInfo in workInfos) {
                when (workInfo.state) {
                    WorkInfo.State.ENQUEUED -> {
                        val tagName = workInfo.tags.firstOrNull() ?: "UnknownTag"
                        Log.d("WorkStatus", "Work with tag $tagName is ENQUEUED")
                    }

                    WorkInfo.State.RUNNING -> {
                        val tagName = workInfo.tags.firstOrNull() ?: "UnknownTag"
                        Log.d("WorkStatus", "Work with tag $tagName is RUNNING")
                    }

                    WorkInfo.State.SUCCEEDED -> {
                        val tagName = workInfo.tags.firstOrNull() ?: "UnknownTag"
                        Log.d("WorkStatus", "Work with tag $tagName has SUCCEEDED")
                    }

                    WorkInfo.State.FAILED -> {
                        val tagName = workInfo.tags.firstOrNull() ?: "UnknownTag"
                        Log.d("WorkStatus", "Work with tag $tagName has FAILED")
                    }

                    else -> {}
                }
            }
        }
        workStatusObserver.observeWorkInfoForUniqueWork(workManager, WORK_NAME)
    }
}