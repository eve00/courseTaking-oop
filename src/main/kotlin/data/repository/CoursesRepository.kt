package data.repository

import domain.entity.Course
import domain.entity.CourseId
import domain.entity.common.Faculty

interface CoursesRepository {
    suspend fun findAll(): List<Course>
    suspend fun findById(courseId: CourseId): Course
    suspend fun findByFaculty(faculty: Faculty): List<Course>
}

class CoursesRepositoryImpl : CoursesRepository {
    override suspend fun findAll(): List<Course> {
        TODO("Not yet implemented")
    }

    override suspend fun findById(courseId: CourseId): Course {
        TODO("Not yet implemented")
    }

    override suspend fun findByFaculty(faculty: Faculty): List<Course> {
        TODO("Not yet implemented")
    }
}
