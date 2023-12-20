package domain.service

import domain.entity.CourseId
import domain.entity.CourseTakingApplicationId
import domain.entity.StudentId
import org.http4k.core.Request
interface CourseTakingApplicationService {
    val firstServedState :Boolean
    suspend fun applyCourseTaking(courseTakingApplicationId: CourseTakingApplicationId, studentId:StudentId, courseId: CourseId)

    suspend fun cancelCourseTaking(studentId:StudentId,courseTakingApplicationId: CourseTakingApplicationId)

}