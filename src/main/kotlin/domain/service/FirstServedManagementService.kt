package domain.service

import domain.entity.Course

interface FirstServedManagementService {
    suspend fun getCoursesCanTake(): List<Course>
}
