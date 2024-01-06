package domain.service.impl

import data.repository.CourseTakingApplicationsRepository
import data.repository.CoursesRepository
import domain.entity.Course
import domain.entity.CourseId
import domain.service.FirstServedManagementService

class FirstServedManagementServiceImpl(
    private val courseTakingApplicationsRepository: CourseTakingApplicationsRepository,
    private val coursesRepository: CoursesRepository
) : FirstServedManagementService {
    override suspend fun getCoursesCanTake(): List<Course> {
        return coursesRepository.findAll()
            .filter { course ->
                courseTakingApplicationsRepository.findByCourseId(course.getId()).size < course.getMax()
        }
    }

    override suspend fun checkCanTake(courseId:CourseId): Boolean {
        return coursesRepository.findById(courseId).getMax() >
        courseTakingApplicationsRepository.findByCourseId(courseId).size
    }
}