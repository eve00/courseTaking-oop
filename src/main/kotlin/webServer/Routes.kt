package webServer

import domain.entity.CourseId
import domain.entity.CourseTakingApplication
import domain.entity.CourseTakingApplicationId
import domain.entity.StudentId
import domain.service.CourseService
import domain.service.CourseTakingApplicationService
import kotlinx.coroutines.*
import org.http4k.core.*
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import java.util.*
import org.http4k.core.ContentType.Companion.APPLICATION_JSON
import org.http4k.core.Status.Companion.OK

/*
* TODO:
* 抽選、先着管理、登録、科目取得
* */
class CourseTakingApplication(
    val courseTakingApplicationService: CourseTakingApplicationService,
    val courseService: CourseService
) : HttpHandler {
    override fun invoke(request: Request): Response = httpHandler(request)

    val httpHandler = routes(
        "/ping" bind Method.GET to { Response(Status.OK) },
        /*QUERY*/
        "/course" bind Method.GET to ::getCourses,
        "/application/{user}" bind Method.GET to ::getApplications,
        /*courseTaking*/
        "/application/{user}" bind Method.POST to ::applyCourseTaking,
        "/application/{user}" bind Method.DELETE to ::cancelCourseTaking,
        /*courseRegistration*/
        "/course/{courseId}" bind Method.GET to ::drawAndRegisterCourseMembers,
        "/course/{courseId}" bind Method.PATCH to ::addCourseCapacity,
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
            return Response(Status.BAD_REQUEST)
        }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun getApplications(request: Request): Response {
        /*requestからuserを取得*/
        val result = CoroutineScope(Dispatchers.IO).async {
            runCatching {
                val studentId: StudentId = StudentId(request.path("studentId") ?: "")

                courseTakingApplicationService.getCourseTakingApplicationList(studentId)
            }
        }

        /*responseを返す*/
        return if (result.getCompleted().isSuccess) {
            JsonData("Successed").toOKResponse()
        } else {
            /*TODO: エラーハンドリング*/
            return Response(Status.BAD_REQUEST)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun applyCourseTaking(request: Request): Response {
        val result = CoroutineScope(Dispatchers.IO).async {
            runCatching {
                /*requestからuser, applicationを取得*/
                val studentId: StudentId = StudentId(request.path("studentId") ?: "")
                val courseId: CourseId = CourseId(request.path("courseId") ?: "")

                val courseTakingApplicationId = CourseTakingApplicationId(UUID.randomUUID().toString())
                courseTakingApplicationService.applyCourseTaking(
                    courseTakingApplicationId,
                    studentId,
                    courseId
                )

            }
        }

        /*responseを返す*/

        return if (result.getCompleted().isSuccess) {
            JsonData("Successed").toOKResponse()
        } else {
            /*TODO: エラーハンドリング*/
            return Response(Status.BAD_REQUEST)
        }

    }

    //Request -> User,Application -> Result -> Response
    private fun cancelCourseTaking(request: Request): Response {
        val result = CoroutineScope(Dispatchers.IO).async {
            runCatching {

            }

        }        /*requestからapplicationIdを取得*/


        /*responseを返す*/
        return if (result.getCompleted().isSuccess) {
            JsonData("Successed").toOKResponse()
        } else {
            /*TODO: エラーハンドリング*/
            return Response(Status.BAD_REQUEST)
        }
    }

    private fun drawAndRegisterCourseMembers(request: Request): Response {
        TODO(
            "requestからcourseidを取得" +
                    "courseIdからcourseTakingApplicationリストを取得" +
                    "抽選する" +
                    "結果を返す"
        )
        /*requestからcourseIdを取得*/

        /*courseIdからcourseTakingApplicationリストを取得*/

        /*抽選する*/

        /*結果を返す*/

    }

    private fun addCourseCapacity(request: Request): Response {
        TODO()
        /*requestから追加するcapacityを取得*/

        /**/

        /*responseを返す*/
    }

    private fun registerCourseMembers(request: Request): Response {
        TODO()
        /*requestからcourseIdを取得*/

        /*対応するcourseに対して登録する*/

        /*responseを返す*/
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
