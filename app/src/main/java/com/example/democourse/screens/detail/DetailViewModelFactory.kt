package com.example.democourse.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.democourse.data.repo.CourseRepository

class CourseDetailsViewModelFactory(
    private val repository: CourseRepository,
    private val courseId: Int
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(
                CourseDetailsViewModel::class.java
            )
        ) {
            @Suppress("UNCHECKED_CAST")
            return CourseDetailsViewModel(
                repository,
                courseId
            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}