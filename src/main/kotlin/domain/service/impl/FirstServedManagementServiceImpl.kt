package domain.service.impl

import data.repository.CourseTakingApplicationsRepository
import domain.entity.Course
import domain.entity.CourseId
import domain.service.CourseService
import domain.service.FirstServedManagementService

class FirstServedManagementServiceImpl(
    val repository: CourseTakingApplicationsRepository,
    val courseService: CourseService
) : FirstServedManagementService {
    override suspend fun checkCanTake(courseId: CourseId): Boolean {
        return repository.findByCourseId(courseId).size < courseService.getCourse(courseId).getMax()
    }

    override suspend fun getCoursesCanTake(): List<Course> {
        return courseService.getCourses()
            .filter { course ->
            repository.findByCourseId(course.getId()).size < course.getMax()
        }
    }

}