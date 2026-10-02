package com.example.democourse.data.remote

import com.example.democourse.data.model.CourseDto

interface CourseApi {
    suspend fun getCourses(): List<CourseDto>

    suspend fun getCourse(courseId: Int): CourseDto
}