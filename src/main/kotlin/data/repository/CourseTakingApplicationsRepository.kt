package data.repository

import domain.entity.CourseId
import domain.entity.CourseTakingApplication
import domain.entity.CourseTakingApplicationId
import domain.entity.StudentId

interface CourseTakingApplicationsRepository {

    suspend fun findByStudentId(studentId: StudentId): List<CourseTakingApplication>
    suspend fun findByCourseId(courseId: CourseId): List<CourseTakingApplication>
    suspend fun findByCourseTakingApplicationId(courseTakingApplicationId: CourseTakingApplicationId): CourseTakingApplication
    suspend fun save(courseTakingApplication: CourseTakingApplication)
    suspend fun delete(courseTakingApplication: CourseTakingApplication)
}

class CourseTakingApplicationsRepositoryImpl() : CourseTakingApplicationsRepository {
    override suspend fun findByStudentId(studentId: StudentId): List<CourseTakingApplication> {
        TODO("Not yet implemented")
    }

    override suspend fun findByCourseId(courseId: CourseId): List<CourseTakingApplication> {
        TODO("Not yet implemented")
    }

    override suspend fun findByCourseTakingApplicationId(courseTakingApplicationId: CourseTakingApplicationId): CourseTakingApplication {
        TODO("Not yet implemented")
    }

    override suspend fun save(courseTakingApplication: CourseTakingApplication) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(courseTakingApplication: CourseTakingApplication) {
        TODO("Not yet implemented")
    }
}
