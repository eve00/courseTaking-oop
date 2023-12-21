package domain.service.impl

import data.repository.CourseTakingApplicationListsRepository
import domain.entity.*
import domain.service.CourseTakingApplicationService
import org.http4k.core.Request

/*
* 履修の申請に関する機能を提供するクラス
*
* */
class CourseTakingApplicationServiceImpl(
    val repository: CourseTakingApplicationListsRepository
) : CourseTakingApplicationService {

    override val firstServedState: Boolean
        get() = TODO("Not yet implemented")

    override suspend fun applyCourseTaking(
        courseTakingApplicationId: CourseTakingApplicationId,
        studentId: StudentId,
        courseId: CourseId
    ) {
        val courseTakingApplicationList = getCourseTakingApplicationList(studentId)
        courseTakingApplicationList.createCourseTakingApplication(courseTakingApplicationId, courseId)
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

    override suspend fun getCourseTakingApplicationList(studentId: StudentId): CourseTakingApplicationList {
        return repository.findByStudentId(studentId)
    }
}