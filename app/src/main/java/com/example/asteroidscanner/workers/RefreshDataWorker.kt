package com.example.asteroidscanner.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.asteroidscanner.data.database.getDatabase
import com.example.asteroidscanner.data.repository.AsteroidRepository
import com.example.asteroidscanner.shared.Utils
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext,params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }


    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = AsteroidRepository(database)


        return try {
            repository.refreshAsteroids(Utils.getCurrentDate())
            Result.success()
        } catch (exception: HttpException) {
            Result.retry()
        }
    }
    }

