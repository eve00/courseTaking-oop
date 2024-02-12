package domain.entity

import domain.entity.common.Identifier

typealias CourseTakingApplicationId = Identifier<CourseTakingApplication, String>

class CourseTakingApplication(
    private val id: CourseTakingApplicationId,
    private val studentId: StudentId,
    private val course: Course,
    private var state: State
) {

    fun getId(): CourseTakingApplicationId {
        return id
    }

    fun getStudentId(): StudentId {
        return studentId
    }

    fun getCourse(): Course {
        return course
    }

    fun getState(): State {
        return state
    }

    /*抽選で当選する*/
    fun confirm() {
        if (state == State.UNCONFIRMED)
            state = State.CONFIRMED
    }

    /*抽選で落選する*/
    fun invalidate() {
        if (state == State.UNCONFIRMED)
            state = State.INVALIDATED
    }


}

enum class State {
    UNCONFIRMED, CONFIRMED, INVALIDATED
}

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