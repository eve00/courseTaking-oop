package webServer

import data.repository.CoursesRepository
import domain.entity.*
import domain.service.CourseRegistrationService
import domain.service.CourseTakingApplicationService
import domain.service.impl.FirstServedManagementServiceImpl
import kotlinx.coroutines.*
import org.http4k.core.*
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import java.util.*
import org.http4k.core.Status
import org.http4k.core.Status.Companion.OK

/*
* TODO:
* 抽選、先着管理、登録、科目取得
* */
class CourseTakingAndRegistration(
    private val courseTakingApplicationService: CourseTakingApplicationService,
    private val firstServedManagementServiceImpl: FirstServedManagementServiceImpl,
    private val courseRegistrationService: CourseRegistrationService,
    private val coursesRepository: CoursesRepository
) : HttpHandler {
    override fun invoke(request: Request): Response = httpHandler(request)

    val httpHandler = routes(
        /*申請履歴*/
        "/application/{studentId}" bind Method.GET to ::getApplications,
        "/course" bind Method.GET to ::getCourses,
        /*先着申請可能な科目*/
        "/course" bind Method.GET to ::getCoursesCanTake,
        /*申請*/
        "/application/createApplication" bind Method.POST to ::applyCourseTaking,
        "/application/cancelApplication" bind Method.DELETE to ::cancelCourseTaking,
        /*抽選・登録*/
        "/course/{courseId}/drawAndRegisterMembers" bind Method.POST to ::drawAndRegisterCourseMembers,
        "/course/{courseId}/registerMembers" bind Method.POST to ::registerCourseMembers,
    )


    private fun getCourses(request: Request): Response {
        val result = CoroutineScope(Dispatchers.IO).async {
            runCatching {
                coursesRepository.findAll()
            }
        }

        /*responseを返す*/
        return if (result.getCompleted().isSuccess) {
            Response(OK)
        } else {
            Response(Status.BAD_REQUEST)
        }
    }

    private fun getCoursesCanTake(request: Request): Response {
        val result = CoroutineScope(Dispatchers.IO).async {
            runCatching {
                firstServedManagementServiceImpl.getCoursesCanTake()
            }
        }

        /*responseを返す*/
        return if (result.getCompleted().isSuccess) {
            Response(OK)
        } else {
            Response(Status.BAD_REQUEST)
        }
    }

    /*
* {studentId}
* */
    private fun getApplications(request: Request): Response {
        val result = CoroutineScope(Dispatchers.IO).async {
            runCatching {
                /*requestからstudentIdを取得*/
                val studentId: StudentId = StudentId(request.path("studentId") ?: "")

                courseTakingApplicationService.getCourseTakingApplications(studentId)
            }
        }

        /*responseを返す*/
        return if (result.getCompleted().isSuccess) {
            Response(OK)
        } else {
            Response(Status.BAD_REQUEST)
        }
    }

    /*
    * {studentId, courseId}
    * */
    private fun applyCourseTaking(request: Request): Response {
        val result = CoroutineScope(Dispatchers.IO).async {
            runCatching {
                /*requestからuser, applicationを取得*/
                val studentId: StudentId = StudentId(request.path("studentId") ?: "")
                val courseId: CourseId = CourseId(request.path("courseId") ?: "")

                /*申請*/
                courseTakingApplicationService.applyCourseTaking(
                    CourseTakingApplicationId(UUID.randomUUID().toString()),
                    studentId,
                    courseId
                )
            }
        }

        /*responseを返す*/
        return if (result.getCompleted().isSuccess) {
            Response(OK)
        } else {
            Response(Status.BAD_REQUEST)
        }
    }

    /*
    * {courseTakingApplicationId}
    * */
    private fun cancelCourseTaking(request: Request): Response {
        val result = CoroutineScope(Dispatchers.IO).async {
            runCatching {
                /*requestからapplicationIdを取得*/
                val courseTakingApplicationId: CourseTakingApplicationId =
                    CourseTakingApplicationId(request.path("courseTakingApplicationId") ?: "")

                /*申請のキャンセル*/
                courseTakingApplicationService.cancelCourseTaking(
                    courseTakingApplicationId,
                )
            }
        }

        /*responseを返す*/
        return if (result.getCompleted().isSuccess) {
            Response(OK)
        } else {
            Response(Status.BAD_REQUEST)
        }
    }

    private fun drawAndRegisterCourseMembers(request: Request): Response {
        val result = CoroutineScope(Dispatchers.IO).async {
            runCatching {
                /*requestからcourseIdを取得*/
                val courseId: CourseId = CourseId(request.path("courseId") ?: "")
                /*抽選する*/
                courseRegistrationService.drawingAndRegisterMembers(courseId)
            }
        }

        /*結果を返す*/
        return if (result.getCompleted().isSuccess) {
            Response(OK)
        } else {
            Response(Status.BAD_REQUEST)
        }

    }

    private fun registerCourseMembers(request: Request): Response {
        val result = CoroutineScope(Dispatchers.IO).async {
            runCatching {
                /*requestからcourseIdを取得*/
                val courseId: CourseId = CourseId(request.path("courseId") ?: "")
                /*抽選する*/
                courseRegistrationService.registerMembers(courseId)
            }
        }


        /*結果を返す*/
        return if (result.getCompleted().isSuccess) {
            Response(OK)
        } else {
            Response(Status.BAD_REQUEST)
        }
    }
}
