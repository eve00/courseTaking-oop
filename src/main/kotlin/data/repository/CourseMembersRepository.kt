package data.repository

import domain.entity.CourseId
import domain.entity.Student

class CourseMembersRepositoryImpl() : CourseMembersRepository {
    override fun save(courseId: CourseId, courseMemberList: List<Student>) {
    }
}

interface CourseMembersRepository {
    fun save(courseId: CourseId, courseMemberList: List<Student>)
}
