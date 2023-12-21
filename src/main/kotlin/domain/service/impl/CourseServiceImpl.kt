package domain.service.impl

import data.repository.CoursesRepository
import domain.entity.Course
import domain.entity.CourseId
import domain.service.CourseService

class CourseServiceImpl(val repository: CoursesRepository): CourseService {
    override suspend fun getCourses(): List<Course> {
        return repository.findAll()
    }

    override suspend fun updateCapacity(courseId: CourseId, capacity:Int) {
        val course = getCourse(courseId)
        course.updateCapacity(capacity)

        repository.save(course)
    }

    private suspend fun getCourse(courseId: CourseId):Course{
        return repository.findById(courseId)
    }

}