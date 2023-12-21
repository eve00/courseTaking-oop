package data.repository

import domain.entity.*

interface CourseTakingApplicationListsRepository {

    /*TODO: ApplicationList生成生成責務はここが持っている*/
    suspend fun findByStudentId(studentId: StudentId): CourseTakingApplicationList
    suspend fun findByCourseId(courseId: CourseId): CourseTakingApplicationList
    suspend fun save(courseTakingApplicationList: CourseTakingApplicationList)

}