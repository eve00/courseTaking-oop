package domain.service.impl

import data.repository.CourseMembersRepository
import data.repository.CourseTakingApplicationListsRepository
import data.repository.StudentsRepository
import domain.entity.CourseId
import domain.entity.CourseTakingApplication
import domain.entity.Student
import domain.entity.StudentId
import domain.service.CourseRegistrationService
import domain.service.CourseService

class CourseRegistrationServiceImpl(
    private val courseService: CourseService,
    private val courseTakingApplicationListsRepository: CourseTakingApplicationListsRepository,
    private val courseMembersRepository: CourseMembersRepository,
    private val studentsRepository: StudentsRepository
) : CourseRegistrationService {


    override suspend fun drawingAndRegisterMembers(courseId: CourseId) {
        val max = courseService.getCourse(courseId).getMax()
        val memberIds =
            courseTakingApplicationListsRepository.findByCourseId(courseId).getCourseTakingApplicationList().shuffled()
                .subList(0, max - 1).map { courseTakingApplication ->
                    courseTakingApplication.getStudentId()
                }
        val members = studentsRepository.findAll().filter { student ->
            memberIds.contains(student.getId())
        }

        courseMembersRepository.save(courseId, members)
        /*TODO: キャンセル処理*/
    }

    override suspend fun registerMembers(courseId: CourseId) {
        val memberIds =
            courseTakingApplicationListsRepository.findByCourseId(courseId).getCourseTakingApplicationList()
                .map { courseTakingApplication ->
                    courseTakingApplication.getStudentId()
                }
        val members = studentsRepository.findAll().filter { student ->
            memberIds.contains(student.getId())
        }

        courseMembersRepository.save(courseId, members)
    }
}