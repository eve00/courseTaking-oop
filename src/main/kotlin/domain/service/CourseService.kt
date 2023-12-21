package domain.service

import domain.entity.Course
import domain.entity.CourseId

interface CourseService {
    suspend fun getCourses(): List<Course>

    suspend fun updateCapacity(courseId: CourseId,capacity:Int)
}