package com.example.democourse.data.repo

import com.example.democourse.data.local.CourseDao
import com.example.democourse.data.local.CourseEntity

import com.example.democourse.data.remote.CourseApi
import com.example.democourse.services.NetworkMonitor

class CourseRepository(
    private val api: CourseApi,
    private val dao: CourseDao,
    private val networkMonitor: NetworkMonitor
) {
    suspend fun getCourses(): List<CourseEntity> {
        return if (networkMonitor.isOnline()){
            getRemoteCourses()
        } else{
            getLocalCourses()
        }
    }

    private suspend fun getRemoteCourses(): List<CourseEntity>{
        val response = api.getCourses()
        val courses = response.map { it.toEntity()}
        dao.insertCourses(courses)
        return courses
    }

    private suspend fun getLocalCourses(): List<CourseEntity>{
       return dao.getCourses()
    }

    suspend fun getCourse(courseId: Int): CourseEntity {
        return api.getCourse(courseId).toEntity()
    }
}