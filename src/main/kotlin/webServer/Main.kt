package webServer

import data.repository.*
import domain.service.impl.CourseRegistrationServiceImpl
import domain.service.impl.CourseTakingApplicationServiceImpl
import domain.service.impl.FirstServedManagementServiceImpl
import org.http4k.server.Jetty
import org.http4k.server.asServer


fun main(args: Array<String>) {
    CourseTakingAndRegistration(
        CourseTakingApplicationServiceImpl(
            CourseTakingApplicationsRepositoryImpl(),
        ),
        FirstServedManagementServiceImpl(
            CourseTakingApplicationsRepositoryImpl(),
            CoursesRepositoryImpl(),
        ),
        CourseRegistrationServiceImpl(
            CourseTakingApplicationsRepositoryImpl(),
            CourseMembersRepositoryImpl(),
            CoursesRepositoryImpl(),
            StudentsRepositoryImpl(),
        ),
        CoursesRepositoryImpl(),
    ).asServer(Jetty(8080)).start()

    println("Server started at http://localhost:8080")

}








