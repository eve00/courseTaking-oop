package data.repository

import domain.entity.CourseId
import domain.entity.Student

interface CourseMembersRepository {
    fun save(courseId: CourseId, courseMemberList:List<Student>)
}