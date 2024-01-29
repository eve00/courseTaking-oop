package latestModel

class CourseManager(
    val course: Course,
    val applications:List<Application>
) {
    fun canApplyAsFirstserve():Boolean = true

    fun selectApplications(){
        /*落選したApplicationをRejected状態にする*/
        applications.subList(0,3).forEach { it.markAsRejected() }
    }

    fun registerMembers(){
        /*登録するApplicationをRegistered状態にする*/
        applications.filterNot { it.getStatus() == Status.REJECTED }.forEach { it.markAsRegistered() }
    }
}