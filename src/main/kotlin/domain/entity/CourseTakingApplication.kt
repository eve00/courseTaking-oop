package domain.entity

import domain.entity.common.Identifier

typealias CourseTakingApplicationId = Identifier<CourseTakingApplication, String>

class CourseTakingApplication(
    private val id: CourseTakingApplicationId,
    private val studentId: StudentId,
    private val courseId: CourseId,
    private val state: State
) {
    private var _state = state

    fun getId(): CourseTakingApplicationId {
        return id
    }

    fun getStudentId(): StudentId {
        return studentId
    }

    fun getCourseId(): CourseId {
        return courseId
    }

    fun getState(): State {
        return _state
    }

    /*抽選で当選する*/

    fun confirm() {
        if (_state == State.UNCONFIRMED)
            _state = State.CONFIRMED
    }

    /*抽選で落選する*/
    fun invalidate() {
        if (_state == State.UNCONFIRMED)
            _state = State.INVALIDATED
    }


}

enum class State {
    UNCONFIRMED, CONFIRMED, INVALIDATED
}