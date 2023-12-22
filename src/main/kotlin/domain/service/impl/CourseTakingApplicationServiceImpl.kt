package domain.service.impl

import data.repository.CourseTakingApplicationsRepository
import domain.entity.*
import domain.service.CourseTakingApplicationService
import domain.service.FirstServedManagementService

/*
* 履修の申請に関する機能を提供するクラス
*
* */
class CourseTakingApplicationServiceImpl(
    val repository: CourseTakingApplicationsRepository,
) : CourseTakingApplicationService {

    override suspend fun applyCourseTaking(
        courseTakingApplicationId: CourseTakingApplicationId,
        studentId: StudentId,
        courseId: CourseId
    ) {
        val newCourseTakingApplication =
            CourseTakingApplication(courseTakingApplicationId, studentId, courseId, State.UNCONFIRMED)
        repository.save(newCourseTakingApplication)
    }

    override suspend fun cancelCourseTaking(
        courseTakingApplicationId: CourseTakingApplicationId
    ) {
        val canceledCourseTakingApplication = repository.findByCourseTakingApplicationId(courseTakingApplicationId)
        repository.delete(canceledCourseTakingApplication)
    }

    override suspend fun getCourseTakingApplications(studentId: StudentId): List<CourseTakingApplication> {
        return repository.findByStudentId(studentId)
    }

}