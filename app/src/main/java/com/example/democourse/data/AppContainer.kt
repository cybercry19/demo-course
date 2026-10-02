package com.example.democourse.data

import android.content.Context
import androidx.room.Room
import com.example.democourse.data.local.AppDatabase
import com.example.democourse.data.remote.MockApiService
import com.example.democourse.data.repo.CourseRepository
import com.example.democourse.services.NetworkMonitor

class AppContainer(context: Context) {

    private val database: AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "learning_database"
        ).build()

    private val apiService = MockApiService()

    private val networkMonitor =
        NetworkMonitor(context)

    private val courseDao =
        database.courseDao()

    val courseRepository =
        CourseRepository(
            api = apiService,
            dao = courseDao,
            networkMonitor = networkMonitor
        )
}