package latestModel


/*システムで扱うクラス*/
class Application(
    val id: String = "",
    val student: Student = Student(),
    val course: Course = Course(),
    private var status: Status = Status.CREATED,
) {
    fun getStatus() = status
    fun markAsRegistered() {
        status = Status.REGISTERED
    }

    fun markAsRejected() {
        status = Status.REJECTED
    }
}

enum class Status {
    CREATED,
    REGISTERED,
    REJECTED
}
