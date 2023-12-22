package webserver

import data.repository.CourseMembersRepositoryImpl
import data.repository.CourseTakingApplicationsRepositoryImpl
import data.repository.CoursesRepositoryImpl
import data.repository.StudentsRepositoryImpl
import domain.service.impl.CourseRegistrationServiceImpl
import domain.service.impl.CourseTakingApplicationServiceImpl
import domain.service.impl.FirstServedManagementServiceImpl
import org.http4k.server.Jetty
import org.http4k.server.asServer

const val PORT = 8080
fun main(args: Array<String>) {
    CourseTakingAndRegistration(
        CourseTakingApplicationServiceImpl(
            CourseTakingApplicationsRepositoryImpl()
        ),
        FirstServedManagementServiceImpl(
            CourseTakingApplicationsRepositoryImpl(),
            CoursesRepositoryImpl()
        ),
        CourseRegistrationServiceImpl(
            CourseTakingApplicationsRepositoryImpl(),
            CourseMembersRepositoryImpl(),
            CoursesRepositoryImpl(),
            StudentsRepositoryImpl()
        ),
        CoursesRepositoryImpl()
    ).asServer(Jetty(PORT)).start()

    println("Server started at http://localhost:8080")
}
