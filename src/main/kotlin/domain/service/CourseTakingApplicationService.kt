package domain.service

import domain.entity.*

interface CourseTakingApplicationService {
    suspend fun applyCourseTaking(courseTakingApplicationId: CourseTakingApplicationId, studentId:StudentId, courseId: CourseId)
    suspend fun cancelCourseTaking(courseTakingApplicationId: CourseTakingApplicationId)
    suspend fun getCourseTakingApplications(studentId: StudentId):  List<CourseTakingApplication>
    }