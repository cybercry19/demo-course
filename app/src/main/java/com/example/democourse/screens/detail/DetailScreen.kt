package com.example.democourse.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.democourse.data.local.CourseEntity
import com.example.democourse.data.repo.CourseRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailsScreen(
    courseId: Int,
    repository: CourseRepository
) {

    val viewModel: CourseDetailsViewModel = viewModel(
        factory = CourseDetailsViewModelFactory(
            repository = repository,
            courseId = courseId
        )
    )

    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Course Details")
                }
            )
        }
    ) { innerPadding ->

        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                   CircularProgressIndicator()
                }
            }

            state.errorMessage != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(state.errorMessage!!)
                }
            }

            state.course != null -> {
                CourseDetailsContent(
                    course = state.course!!,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
private fun CourseDetailsContent(
    course: CourseEntity,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = course.title,
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Instructor: ${course.instructor}"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Progress: ${course.progress}%"
        )

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = {
                course.progress / 100f
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Lessons: ${course.lessons}"
        )
    }
}