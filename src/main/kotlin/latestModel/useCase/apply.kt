package latestModel.useCase

import latestModel.*
import latestModel.dataStore.ApplicationsDataStore


fun apply(
    studentId: String,
    courseId: String,
) {
    /*IO*/
    val applications = listOf<Application>()
    val myApplications = listOf<Application>()
    val course = Course(courseId)
    val student = Student(studentId)

    /*aggregate*/
    val courseSchedule = CourseSchedule(myApplications)
    courseSchedule.apply(student, course)

    /*IO*/
    //dataStore.save(newApplication)
}

