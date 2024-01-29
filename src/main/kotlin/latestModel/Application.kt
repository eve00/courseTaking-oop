package latestModel


/*システムで扱うクラス*/
class Application(
    val id: String,
    val student: Student,
    val course: Course,
    private var status: Status,
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
    NOT_REGISTERED,
    REGISTERED,
    REJECTED
}

/*永続化するデータのクラス*/
class ApplicationData(
    val id: String,
    val studentId: String,
    val courseId: String,
    private var status: Status,
)