package domain.service

import domain.entity.*

interface CourseRegistrationService {
    suspend fun drawingAndRegisterMembers(courseId: CourseId)
    suspend fun registerMembers(courseId: CourseId)
}
