package webServer

import domain.entity.*
import domain.entity.CourseTakingApplication
import domain.service.CourseRegistrationService
import domain.service.CourseService
import domain.service.CourseTakingApplicationService
import kotlinx.coroutines.*
import org.http4k.core.*
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import java.util.*
import org.http4k.core.ContentType.Companion.APPLICATION_JSON
import org.http4k.core.Status
import org.http4k.core.Status.Companion.OK

/*
* TODO:
* 抽選、先着管理、登録、科目取得
* */
class CourseTaking(
    private val courseTakingApplicationService: CourseTakingApplicationService,
    private val courseRegistrationService: CourseRegistrationService,
    private val courseService: CourseService
) : HttpHandler {
    override fun invoke(request: Request): Response = httpHandler(request)

    val httpHandler = routes(
        "/ping" bind Method.GET to { Response(Status.OK) },
        /*QUERY*/
        "/course" bind Method.GET to ::getCourses,
        "/course" bind Method.GET to ::getCourses,
        "/application" bind Method.GET to ::getApplications,
        /*courseTaking*/
        "/application" bind Method.POST to ::applyCourseTaking,
        "/application" bind Method.DELETE to ::cancelCourseTaking,
        /*courseRegistration*/
        "/course/{courseId}" bind Method.GET to ::drawAndRegisterCourseMembers,
        "/course/{courseId}" bind Method.POST to ::registerCourseMembers,
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun getCourses(request: Request): Response {
        val result = CoroutineScope(Dispatchers.IO).async {
            runCatching {
                courseService.getCourses()
            }
        }

        /*responseを返す*/
        return if (result.getCompleted().isSuccess) {
            JsonData("Success").toOKResponse()
        } else {
            /*TODO: エラーハンドリング*/
            Response(Status.BAD_REQUEST)
        }
    }

    /*
* {studentId}
* */
    @OptIn(ExperimentalCoroutinesApi::class)
    private fun getApplications(request: Request): Response {
        /*requestからuserを取得*/
        val result = CoroutineScope(Dispatchers.IO).async {
            runCatching {
                val studentId: StudentId = StudentId(request.path("studentId") ?: "")

                courseTakingApplicationService.getCourseTakingApplications(studentId)
            }
        }

        /*responseを返す*/
        return if (result.getCompleted().isSuccess) {
            Response(OK)
        } else {
            /*TODO: エラーハンドリング*/
            Response(Status.BAD_REQUEST)
        }
    }

    /*
    * {studentId, courseId, applicationFormat}
    * */
    @OptIn(ExperimentalCoroutinesApi::class)
    private fun applyCourseTaking(request: Request): Response {
        val result = CoroutineScope(Dispatchers.IO).async {
            runCatching {
                /*requestからuser, applicationを取得*/
                val studentId: StudentId = StudentId(request.path("studentId") ?: "")
                val courseId: CourseId = CourseId(request.path("courseId") ?: "")
                val applicationFormat: String = request.path("applicationFormat") ?: ""

                val courseTakingApplicationId = CourseTakingApplicationId(UUID.randomUUID().toString())
                when (applicationFormat) {
                    "advanced" -> courseTakingApplicationService.applyCourseTaking(
                        courseTakingApplicationId,
                        studentId,
                        courseId
                    )

                    "first-served" -> courseTakingApplicationService.applyCourseTakingBasedOnFirstserved(
                        courseTakingApplicationId,
                        studentId,
                        courseId
                    )
                }
            }
        }

        /*responseを返す*/

        return if (result.getCompleted().isSuccess) {
            Response(OK)
        } else {
            /*TODO: エラーハンドリング*/
            Response(Status.BAD_REQUEST)
        }

    }

    /*
    * {studentId, courseTakingApplicationId}
    * */
    @OptIn(ExperimentalCoroutinesApi::class)
    private fun cancelCourseTaking(request: Request): Response {
        val result = CoroutineScope(Dispatchers.IO).async {
            runCatching {
                /*requestからuser, applicationを取得*/
                val studentId: StudentId = StudentId(request.path("studentId") ?: "")
                val courseTakingApplicationId: CourseTakingApplicationId =
                    CourseTakingApplicationId(request.path("courseTakingApplicationId") ?: "")

                courseTakingApplicationService.cancelCourseTaking(
                    courseTakingApplicationId,
                )
            }
        }

        /*responseを返す*/

        return if (result.getCompleted().isSuccess) {
            Response(OK)
        } else {
            /*TODO: エラーハンドリング*/
            Response(Status.BAD_REQUEST)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
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
            /*TODO: エラーハンドリング*/
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
            /*TODO: エラーハンドリング*/
            Response(Status.BAD_REQUEST)
        }
    }


    data class JsonData(val raw: String) {
        fun toOKResponse(): Response {
            return Response(OK).body(raw).header("Content-Type", APPLICATION_JSON.toHeaderValue())
        }
    }

    fun convertApplicationListToJson(list: List<CourseTakingApplication>): JsonData {
        /*TODO: serialize list*/
        return JsonData("nothing")
    }

    fun toResponse(data: JsonData): Response =
        Response(Status.OK).body(data.raw)


}
