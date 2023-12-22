package domain.service

import domain.entity.CourseId

interface CourseRegistrationService {
    suspend fun drawingAndRegisterMembers(courseId: CourseId)
    suspend fun registerMembers(courseId: CourseId)
}
