package webServer

import domain.CourseTakingApplication
import domain.CourseTakingHub
import domain.User
import org.http4k.core.*
import org.http4k.core.body.form
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes

/*
* TODO:
* 抽選、先着管理、登録、科目取得
* */
class CourseTaking(val hub: CourseTakingHub): HttpHandler {
    override fun invoke(request: Request): Response = httpHandler(request)

    val httpHandler = routes(
        "/ping" bind Method.GET to { Response(Status.OK) },
        "/application/{user}" bind Method.GET to ::getApplications,
        "/application/{user}" bind Method.POST to ::applyCourseTaking,
        "/application/{user}" bind Method.DELETE to ::cancelCourseTaking,
        "/course" bind Method.GET to ::getCourses,
        "/course/{courseId}" bind Method.GET to ::drawing,
        "/course/{courseId}" bind Method.PATCH to ::updateCourseCapacity,
        "/course/{courseId}" bind Method.POST to ::registerCourseMembers,
    )

    private fun getCourses(request: Request): Response{
        TODO("履修可能な科目を返す")
    }

    //Request -> User -> Result -> Response
    private fun getApplications(request: Request): Response {
        TODO()
    }

    //Request -> User,Application -> Result -> Response
    private fun applyCourseTaking(request: Request): Response {
        TODO()

    }

    //Request -> User,Application -> Result -> Response
    private fun cancelCourseTaking(request: Request): Response {
        TODO()

    }

    private fun drawing(request: Request): Response {
        TODO()

    }

    private fun updateCourseCapacity(request: Request): Response {
        TODO()
    }

    private fun registerCourseMembers(request: Request): Response {
        TODO()
    }



    data class JsonData(val raw:String)
    fun convertApplicationListToJson(list: List<CourseTakingApplication>): JsonData {
        /*TODO: serialize list*/
        return JsonData("nothing")
    }

    fun toResponse(data: JsonData): Response =
        Response(Status.OK).body(data.raw)



}

