package com.example.democourse.data.model

import com.example.democourse.data.local.CourseDao
import com.example.democourse.data.local.CourseEntity

data class CourseDto(
    val id: Int,
    val title: String,
    val instructor: String,
    val progress: Int,
    val lessons: Int
){
  fun  toEntity():CourseEntity{
      return  CourseEntity(id, title, instructor, progress, lessons)
  }
}