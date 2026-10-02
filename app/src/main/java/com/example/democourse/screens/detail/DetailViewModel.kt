package com.example.democourse.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.democourse.data.repo.CourseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CourseDetailsViewModel(
    private val repository: CourseRepository,
    private val courseId: Int
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        CourseDetailsUiState(isLoading = true)
    )

    val uiState: StateFlow<CourseDetailsUiState> =
        _uiState.asStateFlow()

    init {
        loadCourse()
    }

    private fun loadCourse() {
        viewModelScope.launch {

            try {
                val course = repository.getCourse(courseId)

                _uiState.value = CourseDetailsUiState(
                    course = course
                )

            } catch (e: Exception) {

                _uiState.value = CourseDetailsUiState(
                    errorMessage = e.message ?: "Something went wrong"
                )
            }
        }
    }
}