package domain.service.impl

import data.repository.CourseTakingApplicationsRepository
import data.repository.CoursesRepository
import domain.entity.Course
import domain.entity.CourseId
import domain.service.FirstServedManagementService

class FirstServedManagementServiceImpl(
    val repository: CourseTakingApplicationsRepository,
    val coursesRepository: CoursesRepository
) : FirstServedManagementService {
    override suspend fun getCoursesCanTake(): List<Course> {
        return coursesRepository.findAll()
            .filter { course ->
            repository.findByCourseId(course.getId()).size < course.getMax()
        }
    }

}