package com.example.rynnarriola.newsapp.worker

import androidx.work.WorkInfo
import androidx.work.WorkManager

class WorkStatusObserver(private val callback: (List<WorkInfo>) -> Unit) {

    fun observeWorkInfoForUniqueWork(workManager: WorkManager, workName: String) {
        val workInfoLiveData = workManager.getWorkInfosForUniqueWorkLiveData(workName)
        workInfoLiveData.observeForever { workInfos ->
            callback.invoke(workInfos)
        }
    }
}