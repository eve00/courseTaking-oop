package domain.entity

import domain.entity.common.Identifier
import java.util.UUID

typealias CourseTakingApplicationId = Identifier<CourseTakingApplication, String>

class CourseTakingApplication(
    private val id: CourseTakingApplicationId,
    private val studentId: StudentId,
    private val courseId: CourseId,
    status: Status
) {
    private var _status = status

    fun getId(): CourseTakingApplicationId {
        return id
    }

    fun getStudentId(): StudentId {
        return studentId
    }
    fun getCourseId(): CourseId {
        return courseId
    }

    fun getStatus(): Status {
        return _status
    }

    /*登録される*/
    fun confirm(){
        _status = Status.UNCONFIRMED
    }

    /*抽選で落選する*/
    fun invalidate(){
        _status = Status.INVALIDATED
    }


}

enum class Status {
    UNCONFIRMED, CONFIRMED, INVALIDATED
}