package domain.service.impl

import data.repository.CourseMembersRepository
import data.repository.CourseTakingApplicationsRepository
import data.repository.CoursesRepository
import data.repository.StudentsRepository
import domain.entity.CourseId
import domain.entity.CourseTakingApplication
import domain.entity.State
import domain.service.CourseRegistrationService

class CourseRegistrationServiceImpl(
    private val courseTakingApplicationsRepository: CourseTakingApplicationsRepository,
    private val courseMembersRepository: CourseMembersRepository,
    private val coursesRepository: CoursesRepository,
    private val studentsRepository: StudentsRepository
) : CourseRegistrationService {
    override suspend fun drawingAndRegisterMembers(courseId: CourseId) {
        /*抽選*/
        val max = coursesRepository.findById(courseId).getMax()
        val drawedCourseTakingApplications = getCourseTakingApplications(courseId).shuffled()
            .subList(0, max - 1)
        drawedCourseTakingApplications.forEach {
            it.confirm()
        }

        val invalidatedCourseTakingApplications = getCourseTakingApplications(courseId).filter {
            it.getState() == State.UNCONFIRMED
        }
        invalidatedCourseTakingApplications.forEach {
            it.invalidate()
        }

        drawedCourseTakingApplications.forEach { courseTakingApplicationsRepository.save(it) }
        invalidatedCourseTakingApplications.forEach { courseTakingApplicationsRepository.save(it) }

        /*登録*/
        val memberIds =
            drawedCourseTakingApplications.map { courseTakingApplication ->
                courseTakingApplication.getStudentId()
            }
        val members = studentsRepository.findAll().filter { student ->
            memberIds.contains(student.getId())
        }
        courseMembersRepository.save(courseId, members)
    }

    override suspend fun registerMembers(courseId: CourseId) {
        val memberIds =
            getCourseTakingApplications(courseId)
                .map { courseTakingApplication ->
                    courseTakingApplication.getStudentId()
                }
        val members = studentsRepository.findAll().filter { student ->
            memberIds.contains(student.getId())
        }

        courseMembersRepository.save(courseId, members)
    }

    private suspend fun getCourseTakingApplications(courseId: CourseId): List<CourseTakingApplication> {
        return courseTakingApplicationsRepository.findByCourseId(courseId)
    }
}
