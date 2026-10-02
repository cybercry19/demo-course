package com.example.democourse.screens.detail

import com.example.democourse.data.local.CourseEntity

data class CourseDetailsUiState(
    val isLoading: Boolean = false,
    val course: CourseEntity? = null,
    val errorMessage: String? = null
)