package domain.entity

class CourseTakingApplicationList(
) {
    private var _courseTakingApplicationList = mutableListOf<CourseTakingApplication>()
    fun createCourseTakingApplication(courseTakingApplicationId: CourseTakingApplicationId, studentId: StudentId,courseId: CourseId) {
        val newApplication = CourseTakingApplication(courseTakingApplicationId,studentId, courseId, Status.UNCONFIRMED)
        _courseTakingApplicationList.add(newApplication)
    }

    fun deleteCourseTakingApplication(courseTakingApplicationId: CourseTakingApplicationId) {
        _courseTakingApplicationList.removeIf { courseTakingApplication ->
            courseTakingApplication.getId() == courseTakingApplicationId
        }
    }

    fun getCourseTakingApplicationList(): List<CourseTakingApplication>{
        return _courseTakingApplicationList
    }

    fun getSize():Int{
        return _courseTakingApplicationList.size
    }
}