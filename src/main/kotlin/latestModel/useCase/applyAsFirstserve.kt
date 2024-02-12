package latestModel.useCase

import latestModel.*
import latestModel.dataStore.ApplicationsDataStore


fun applyAsFirstServe(
    studentId: String,
    courseId: String,
    dataStore: ApplicationsDataStore
) {
    /*IO*/
    val applications = listOf<Application>()
    val myApplications = listOf<Application>()
    val student = Student(studentId)
    val course = Course(courseId)

    /*aggregate*/
    val courseManager = CourseManager(course, applications)
    val courseSchedule = CourseSchedule(myApplications)
    if (courseManager.canApplyAsFirstserve()) {
        courseSchedule.apply(student, course)
    }

    /*IO*/
    //dataStore.save(newApplication)
}