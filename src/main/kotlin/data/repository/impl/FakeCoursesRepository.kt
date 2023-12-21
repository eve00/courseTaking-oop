package data.repository.impl

import data.repository.CoursesRepository
import domain.entity.Course

class FakeCoursesRepository : CoursesRepository {
    override suspend fun findAll(): List<Course> {
        TODO("Not yet implemented")
    }
}