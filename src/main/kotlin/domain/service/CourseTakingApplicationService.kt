package domain.service

import domain.CourseTakingApplicationList
import domain.entity.*

interface CourseTakingApplicationService {

    suspend fun applyCourseTaking(
        courseTakingApplicationId: CourseTakingApplicationId, studentId: StudentId, courseId: CourseId
    ) : CourseTakingApplicationList?
    suspend fun cancelCourseTaking(studentId: StudentId, courseTakingApplicationId: CourseTakingApplicationId) : CourseTakingApplicationList
    suspend fun getCourseTakingApplications(studentId: StudentId): CourseTakingApplicationList
}
