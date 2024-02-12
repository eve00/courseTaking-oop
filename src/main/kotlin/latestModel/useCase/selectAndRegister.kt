package latestModel.useCase

import latestModel.Application
import latestModel.Course
import latestModel.CourseManager
import latestModel.CourseSchedule
import latestModel.dataStore.ApplicationsDataStore
import latestModel.dataStore.CourseMembersDataStore


fun selectAndRegister(
    courseId: String,
    applicationsDataStore: ApplicationsDataStore,
    courseMembersDataStore: CourseMembersDataStore

){
    /*IO*/
    val applications = listOf<Application>()
    val course = Course(courseId)

    /*aggregate*/
    val courseManager = CourseManager(course,applications)
    courseManager.selectApplications()
    courseManager.registerMembers()

    /*IO*/
    //applicationsDataStore.save(registeredApplication + rejectedApplication)
    //courseMembersDataStore.save(members)
}