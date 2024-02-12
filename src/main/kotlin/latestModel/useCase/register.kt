package latestModel.useCase

import latestModel.Application
import latestModel.Course
import latestModel.CourseManager
import latestModel.dataStore.ApplicationsDataStore
import latestModel.dataStore.CourseMembersDataStore


fun register(
    courseId: String,
){
    /*IO*/
    val applications = listOf<Application>()
    val course = Course(courseId)

    /*aggregate*/
    val courseManager = CourseManager(course,applications)
    courseManager.registerMembers()

    /*IO*/
    //applicationsDataStore.save(registeredApplication)
    //courseMembersDataStore.save(members)
}