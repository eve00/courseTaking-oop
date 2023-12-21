package data.repository

import domain.entity.Course
import domain.entity.CourseId

interface CoursesRepository {
    suspend fun findAll() : List<Course>
    suspend fun findById(courseId: CourseId) : Course
    suspend fun save(course:Course)
}