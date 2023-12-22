package domain.service

import domain.entity.CourseId
import domain.entity.CourseTakingApplication
import domain.entity.CourseTakingApplicationId
import domain.entity.StudentId

interface CourseTakingApplicationService {
    suspend fun applyCourseTaking(courseTakingApplicationId: CourseTakingApplicationId, studentId: StudentId, courseId: CourseId)
    suspend fun cancelCourseTaking(courseTakingApplicationId: CourseTakingApplicationId)
    suspend fun getCourseTakingApplications(studentId: StudentId): List<CourseTakingApplication>
}
