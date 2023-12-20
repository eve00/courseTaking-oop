package domain.entity

import java.util.*

class CourseTakingApplicationList(
    studentId: StudentId
) {
    private var _courseTakingApplicationList = mutableListOf<CourseTakingApplication>()
    fun createCourseTakingApplication(courseTakingApplicationId: CourseTakingApplicationId, courseId: CourseId){
        val newApplication = CourseTakingApplication(courseTakingApplicationId, courseId, Status.UNCONFIRMED)
        _courseTakingApplicationList.add(newApplication)
    }
    fun deleteCourseTakingApplication(courseTakingApplicationId: CourseTakingApplicationId){
        _courseTakingApplicationList.removeIf { courseTakingApplication ->
            courseTakingApplication.getId() == courseTakingApplicationId
        }
    }
}