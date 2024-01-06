package domain.service

import domain.entity.Course
import domain.entity.CourseId

interface FirstServedManagementService {
    suspend fun getCoursesCanTake(): List<Course>
    suspend fun checkCanTake(courseId:CourseId):Boolean
}