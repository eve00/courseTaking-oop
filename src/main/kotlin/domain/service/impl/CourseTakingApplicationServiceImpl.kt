package domain.service.impl

import data.repository.CourseTakingApplicationsRepository

import data.repository.CoursesRepository
import domain.CourseTakingApplicationList
import domain.entity.*

import domain.service.CourseTakingApplicationService

/*
* 履修の申請に関する機能を提供するクラス
*
* */
class CourseTakingApplicationServiceImpl(
    private val courseTakingApplicationsRepository: CourseTakingApplicationsRepository,
    private val coursesRepository: CoursesRepository
) : CourseTakingApplicationService {


    /*申請情報の永続化*/
    override suspend fun applyCourseTaking(
        courseTakingApplicationId: CourseTakingApplicationId,
        studentId: StudentId,
        courseId: CourseId
    ): CourseTakingApplicationList? {
        val courseTakingApplicationList = getCourseTakingApplicationList(studentId)

        val newCourseTakingApplication = createCourseTakingApplication(courseTakingApplicationId,studentId,courseId)
        courseTakingApplicationList.addCourseTakingApplication(newCourseTakingApplication)
        if(courseTakingApplicationList.isWithinLimit()){
            courseTakingApplicationsRepository.save(newCourseTakingApplication)
            return courseTakingApplicationList
        }else{
            throw Exception("取得可能な単位数を超過しています。")
        }
    }

    override suspend fun cancelCourseTaking(
        studentId: StudentId,
        courseTakingApplicationId: CourseTakingApplicationId
    ): CourseTakingApplicationList {
        val courseTakingApplicationList = getCourseTakingApplicationList(studentId)

        courseTakingApplicationList.removeCourseTakingApplication(courseTakingApplicationId)
        courseTakingApplicationsRepository.delete(courseTakingApplicationId)

        return courseTakingApplicationList
    }

    override suspend fun getCourseTakingApplications(studentId: StudentId): CourseTakingApplicationList {
        return getCourseTakingApplicationList(studentId)
    }

    /*CourseTakingApplicationListの生成*/
    private suspend fun getCourseTakingApplicationList(studentId: StudentId): CourseTakingApplicationList {
        val courseTakingApplications = courseTakingApplicationsRepository.findByStudentId(studentId)
        return CourseTakingApplicationList(
            studentId,
            courseTakingApplications.toMutableList(),
            courseTakingApplications.map { courseTakingApplication ->
                courseTakingApplication.getCourse().getCredit()
            }.reduce { acc, credit -> acc + credit }
        )
    }

    /*新たなCourseTakingApplicationの作成*/
    private suspend fun createCourseTakingApplication(
        courseTakingApplicationId: CourseTakingApplicationId,
        studentId: StudentId,
        courseId: CourseId
    ): CourseTakingApplication {
        return  CourseTakingApplication(
            courseTakingApplicationId,
            studentId,
            coursesRepository.findById(courseId),
            State.UNCONFIRMED
        )
    }

}