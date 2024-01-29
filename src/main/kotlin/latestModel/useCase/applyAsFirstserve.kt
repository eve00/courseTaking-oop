package latestModel.useCase

import latestModel.Course
import latestModel.Student
import latestModel.dataStore.ApplicationsDataStore


fun applyAsFirstServe(
    student: Student,
    course: Course,
    dataStore: ApplicationsDataStore
) {
    /*IO*/
    val applicationsOfCourse = dataStore.findByCourseId(course.id)
    val applicationsOfStudent = dataStore.findByStudentId(student.id)



    /*IO*/
    //dataStore.save(newApplication)
}