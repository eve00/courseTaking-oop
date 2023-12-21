package domain.service.impl

import data.repository.CourseTakingApplicationListsRepository
import domain.entity.CourseId
import domain.entity.CourseTakingApplicationId
import domain.entity.CourseTakingApplicationList
import domain.entity.StudentId
import domain.service.CourseTakingApplicationService
import domain.service.FirstServedManagementService

/*
* 履修の申請に関する機能を提供するクラス
*
* */
class CourseTakingApplicationServiceImpl(
    val repository: CourseTakingApplicationListsRepository,
    val firstServedManagementService: FirstServedManagementService
) : CourseTakingApplicationService {

    override suspend fun applyCourseTaking(
        courseTakingApplicationId: CourseTakingApplicationId,
        studentId: StudentId,
        courseId: CourseId
    ) {
        val courseTakingApplicationList = getCourseTakingApplicationList(studentId)
        courseTakingApplicationList.createCourseTakingApplication(courseTakingApplicationId,studentId, courseId)
        repository.save(courseTakingApplicationList)
    }

    override suspend fun cancelCourseTaking(
        studentId: StudentId,
        courseTakingApplicationId: CourseTakingApplicationId
    ) {
        val courseTakingApplicationList = getCourseTakingApplicationList(studentId)
        courseTakingApplicationList.deleteCourseTakingApplication(courseTakingApplicationId)
        repository.save(courseTakingApplicationList)
    }

    override suspend fun applyCourseTakingBasedOnFirstserved(
        courseTakingApplicationId: CourseTakingApplicationId,
        studentId: StudentId,
        courseId: CourseId
    ) {
        if (firstServedManagementService.checkCanTake(courseId)) {
            applyCourseTaking(courseTakingApplicationId, studentId, courseId)
        }
    }

    override suspend fun getCourseTakingApplicationList(studentId: StudentId): CourseTakingApplicationList {
        return repository.findByStudentId(studentId)
    }

}