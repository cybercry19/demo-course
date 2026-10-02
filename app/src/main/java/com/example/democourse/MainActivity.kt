package com.example.democourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.democourse.data.AppContainer
import com.example.democourse.navigation.AppNavigation
import com.example.democourse.ui.theme.DemoCourseTheme

class MainActivity : ComponentActivity() {
    private lateinit var appContainer: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        appContainer = AppContainer(applicationContext)
        setContent {
            DemoCourseTheme {
                AppNavigation(appContainer)
            }
        }
    }
}
