package latestModel.useCase

import latestModel.Course
import latestModel.Student
import latestModel.createApplication
import latestModel.dataStore.ApplicationsDataStore


fun apply(
    student: Student,
    course: Course,
    dataStore: ApplicationsDataStore
){
    /*IO*/
    val applications = dataStore.findByStudentId(student.id)


    /*IO*/
    //dataStore.save(newApplication)
}

