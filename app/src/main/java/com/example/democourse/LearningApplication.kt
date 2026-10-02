package com.example.democourse

import android.app.Application
import com.example.democourse.data.AppContainer

class LearningApplication : Application() {

    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer(this)
    }
}