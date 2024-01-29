package latestModel.useCase

import latestModel.dataStore.ApplicationsDataStore
import latestModel.dataStore.CourseMembersDataStore
import latestModel.workflow.registerApplication


fun register(
    courseId: String,
    capacity: Int,
    applicationsDataStore: ApplicationsDataStore,
    courseMembersDataStore: CourseMembersDataStore
){
    /*IO*/
    val notRegisteredApplication = applicationsDataStore.findByCourseId(courseId)


    /*IO*/
    //applicationsDataStore.save(registeredApplication)
    //courseMembersDataStore.save(members)
}