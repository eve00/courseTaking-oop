package data.repository.impl

import data.repository.CourseTakingApplicationListsRepository
import domain.entity.CourseTakingApplicationList
import domain.entity.StudentId

class FakeCourseTakingApplicationsRepository : CourseTakingApplicationListsRepository {
    override suspend fun findByStudentId(studentId: StudentId): CourseTakingApplicationList {
        TODO("Not yet implemented")
    }

    override suspend fun save(courseTakingApplicationList: CourseTakingApplicationList) {
        TODO("Not yet implemented")
    }
}