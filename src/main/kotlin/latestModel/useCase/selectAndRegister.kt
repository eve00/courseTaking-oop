package latestModel.useCase

import latestModel.dataStore.ApplicationsDataStore
import latestModel.dataStore.CourseMembersDataStore


fun selectAndRegister(
    courseId: String,
    capacity: Int,
    applicationsDataStore: ApplicationsDataStore,
    courseMembersDataStore: CourseMembersDataStore

){
    /*IO*/
    val notRegisteredApplication = applicationsDataStore.findByCourseId(courseId)


    /*IO*/
    //applicationsDataStore.save(registeredApplication + rejectedApplication)
    //courseMembersDataStore.save(members)
}