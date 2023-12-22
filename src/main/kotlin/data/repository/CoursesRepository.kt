package data.repository

import domain.entity.Course
import domain.entity.CourseId
import domain.entity.common.Faculty

interface CoursesRepository {
    suspend fun findAll() : List<Course>
    suspend fun findById(courseId: CourseId) : Course
    suspend fun findByFaculty(faculty: Faculty) : List<Course>
}