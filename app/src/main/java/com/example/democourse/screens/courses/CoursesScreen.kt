package com.example.democourse.screens.courses

import android.telecom.Call.Details
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.democourse.data.local.CourseEntity
import com.example.democourse.data.repo.CourseRepository


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseScreen(
    repository: CourseRepository,
    navCourseDetails: (course:CourseEntity) -> Unit
) {

    val viewModel: CourseViewModel = viewModel(
        factory = CourseViewModelFactory(repository)
    )

    val state by viewModel.currentState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Courses")
                }
            )
        }
    ) { innerPadding ->

        when (state) {

            is CourseLoaded -> {
                val courses =
                    (state as CourseLoaded).list
                if (courses.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No data available")
                    }

                } else {
                    BodyContent(
                        state = state as CourseLoaded,
                        innerPadding = innerPadding,
                        navCourseDetails = navCourseDetails
                    )
                }
            }

            is CourseError -> {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = (state as CourseError).error
                    )
                }
            }

            else -> {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun BodyContent(state:CourseLoaded,innerPadding:PaddingValues,navCourseDetails: (course:CourseEntity) -> Unit){
    LazyColumn(modifier = Modifier.padding(innerPadding)) {
        items(state.list){it-> CourseItem(it,navCourseDetails) }

    }
}

@Composable
fun  CourseItem(course:CourseEntity,navCourseDetails: (course:CourseEntity) -> Unit){
    ListItem(
        modifier = Modifier.padding(bottom = 10.dp),
        trailingContent = {
            ElevatedButton(onClick = {
                navCourseDetails(course)
            },
                modifier = Modifier.width(120.dp)
                ) {
                Text("CONTINUE")
            }
        },
        headlineContent = {
            Column {
                Text(course.title, style = MaterialTheme.typography.bodyLarge)
                Text("${course.instructor} ● Lessons: ${course.lessons}",
                    style = MaterialTheme.typography.labelLarge.copy(color = Color.Gray),
                    modifier = Modifier.padding(top = 5.dp)
                    )
                LinearProgressIndicator(progress = { course.progress / 100f }, modifier = Modifier.fillMaxWidth().padding(top = 10.dp))
            }
        }
    )
}

