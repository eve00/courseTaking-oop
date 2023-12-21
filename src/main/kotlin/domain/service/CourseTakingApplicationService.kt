package domain.service

import domain.entity.*
import org.http4k.core.Request
interface CourseTakingApplicationService {
    suspend fun applyCourseTaking(courseTakingApplicationId: CourseTakingApplicationId, studentId:StudentId, courseId: CourseId)
    suspend fun cancelCourseTaking(studentId:StudentId,courseTakingApplicationId: CourseTakingApplicationId)
    suspend fun applyCourseTakingBasedOnFirstserved(courseTakingApplicationId: CourseTakingApplicationId, studentId:StudentId, courseId: CourseId)
    suspend fun getCourseTakingApplicationList(studentId: StudentId): CourseTakingApplicationList

    }