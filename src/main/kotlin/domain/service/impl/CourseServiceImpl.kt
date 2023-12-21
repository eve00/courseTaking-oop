package domain.service.impl

import data.repository.CoursesRepository
import data.repository.StudentsRepository
import domain.entity.Course
import domain.entity.CourseId
import domain.entity.StudentId
import domain.entity.common.Faculty
import domain.service.CourseService

class CourseServiceImpl(
    private val courseRepository: CoursesRepository,
) : CourseService {
    override suspend fun getCourses(): List<Course> {
        return courseRepository.findAll()
    }

    override suspend fun getCourse(courseId: CourseId): Course {
        return courseRepository.findById(courseId)
    }


}