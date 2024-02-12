package latestModel.useCase

import latestModel.Application
import latestModel.Course
import latestModel.CourseManager
import latestModel.CourseSchedule
import latestModel.dataStore.ApplicationsDataStore

fun cancelApplication(
    applicationId: String,
    applicationsDataStore: ApplicationsDataStore
) {
    /*IO*/
    val application = Application()
    val applications = listOf<Application>()
    val myApplications = listOf<Application>()

    /*aggregate*/
    val courseSchedule = CourseSchedule(CourseManager(application.course,applications),myApplications)
    courseSchedule.cancel(applicationId)

    /*IO*/
    applicationsDataStore.delete(application.id)

}