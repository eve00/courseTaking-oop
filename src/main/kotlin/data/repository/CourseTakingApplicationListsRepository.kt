package data.repository

import domain.entity.CourseTakingApplication
import domain.entity.CourseTakingApplicationId
import domain.entity.CourseTakingApplicationList
import domain.entity.StudentId

interface CourseTakingApplicationListsRepository {

    /*TODO: ApplicationList生成生成責務はここが持っている*/
    suspend fun findByStudentId(studentId: StudentId): CourseTakingApplicationList
    suspend fun save(courseTakingApplicationList: CourseTakingApplicationList)

}