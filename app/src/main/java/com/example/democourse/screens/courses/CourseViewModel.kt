package com.example.democourse.screens.courses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.democourse.data.repo.CourseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CourseViewModel(private val repository: CourseRepository):ViewModel() {
    private val _state = MutableStateFlow<CourseState>(CourseLoading)
    val currentState = _state.asStateFlow()

    init {
        println("VM CREATED: ${hashCode()}")
        getCourse()
    }

    override fun onCleared() {
        println("VM CLEARED: ${hashCode()}")
        super.onCleared()
    }

    fun getCourse() {
        println("GET COURSE: VM=${hashCode()}")

        viewModelScope.launch {
            println("API CALL START")

            _state.emit(CourseLoading)

            try {
                val list = repository.getCourses()

                println("API CALL SUCCESS: ${list.size}")

                _state.emit(CourseLoaded(list))
            } catch (e: Exception) {
                println("API CALL ERROR: ${e.message}")

                _state.emit(
                    CourseError(
                        e.message ?: "Something went wrong"
                    )
                )
            }
        }
    }

}