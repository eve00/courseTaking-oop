package domain.service.impl

import data.repository.CourseTakingApplicationListsRepository
import domain.entity.Course
import domain.entity.CourseId
import domain.service.CourseService
import domain.service.FirstServedManagementService

class FirstServedManagementServiceImpl(
    val repository: CourseTakingApplicationListsRepository,
    val courseService: CourseService
) : FirstServedManagementService {
    override suspend fun checkCanTake(courseId: CourseId): Boolean {
        return repository.findByCourseId(courseId).getSize() < courseService.getCourse(courseId).getMax()
    }

    override suspend fun getCoursesCanTake(): List<Course> {
        return courseService.getCourses()
            .filter { course ->
            repository.findByCourseId(course.getId()).getSize() < course.getMax()
        }
    }

}