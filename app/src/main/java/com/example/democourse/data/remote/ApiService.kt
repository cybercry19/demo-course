package com.example.democourse.data.remote
import com.example.democourse.data.model.CourseDto
import kotlinx.coroutines.delay

class MockApiService : CourseApi {
        override suspend fun getCourses(): List<CourseDto> {
            delay(100)
            return listOf(
                CourseDto(
                    id = 1,
                    title = "Python Programming",
                    instructor = "John Smith",
                    progress = 65,
                    lessons = 20
                ),
                CourseDto(
                    id = 2,
                    title = "Generative AI",
                    instructor = "Sarah Williams",
                    progress = 40,
                    lessons = 16
                ),
                CourseDto(
                    id = 3,
                    title = "Full Stack Development",
                    instructor = "David Brown",
                    progress = 25,
                    lessons = 28
                )
            )
        }

    override suspend fun getCourse(courseId: Int): CourseDto {
        delay(500)

        return getCourses().first {
            it.id == courseId
        }
    }

}