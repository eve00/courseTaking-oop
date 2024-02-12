package latestModel

class CourseManager(
    private val course: Course,
    private val applications:List<Application>
) {
    fun canApplyWithFirstArrival():Boolean = applications.size <= course.capacity

    fun selectApplications(){
        /*落選したApplicationをRejected状態にする*/
        applications.subList(0,3).forEach { it.markAsRejected() }
    }

    fun registerMembers(){
        /*登録するApplicationをRegistered状態にする*/
        applications.filterNot { it.getStatus() == Status.REJECTED }.forEach { it.markAsRegistered() }
    }
}