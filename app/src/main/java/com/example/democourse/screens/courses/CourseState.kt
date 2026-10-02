package com.example.democourse.screens.courses

import com.example.democourse.data.local.CourseEntity

sealed interface CourseState

data object CourseLoading:CourseState

data class CourseError(val error:String):CourseState

data class CourseLoaded(val list:List<CourseEntity>):CourseState

