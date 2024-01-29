package com.example.rynnarriola.newsapp

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.rynnarriola.newsapp.util.Constants.WORK_NAME
import com.example.rynnarriola.newsapp.worker.NewsWorker
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
        scheduleNewsWorker()
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    private fun scheduleNewsWorker() {
        // Check if the work is not already scheduled
        val workStatus = workManager.getWorkInfosForUniqueWorkLiveData(WORK_NAME).value

        if (workStatus.isNullOrEmpty()) {
            Log.d("WorkStatus", "No existing work found. Scheduling new work.")
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val currentTime = Calendar.getInstance()
            val targetTime = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 3) // Set the target hour to 3 AM
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
            }

            val initialDelay = if (currentTime.before(targetTime)) {
                targetTime.timeInMillis - currentTime.timeInMillis
            } else {
                targetTime.timeInMillis + TimeUnit.HOURS.toMillis(24) - currentTime.timeInMillis
            }

            val workRequest = OneTimeWorkRequestBuilder<NewsWorker>()
                .setConstraints(constraints)
                .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
                .build()

            workManager.enqueueUniqueWork(
                WORK_NAME,
                ExistingWorkPolicy.KEEP,
                workRequest
            )
        } else{
            Log.d("WorkStatus", "work found")

        }
    }

}