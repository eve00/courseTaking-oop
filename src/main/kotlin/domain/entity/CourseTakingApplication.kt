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