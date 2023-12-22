package data.repository

import domain.entity.*

interface CourseTakingApplicationsRepository {

    suspend fun findByStudentId(studentId: StudentId): List<CourseTakingApplication>
    suspend fun findByCourseId(courseId: CourseId): List<CourseTakingApplication>
    suspend fun findByCourseTakingApplicationId(courseTakingApplicationId: CourseTakingApplicationId): CourseTakingApplication
    suspend fun save(courseTakingApplication: CourseTakingApplication)
    suspend fun delete(courseTakingApplication: CourseTakingApplication)

}