package domain.service

import domain.entity.Course
import domain.entity.CourseId

interface FirstServedManagementService {
    suspend fun checkCanTake(courseId: CourseId):Boolean

    suspend fun getCoursesCanTake(): List<Course>
}