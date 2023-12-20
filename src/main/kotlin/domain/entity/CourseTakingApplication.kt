package domain.entity

import domain.entity.common.Identifier
import java.util.UUID

typealias CourseTakingApplicationId = Identifier<CourseTakingApplication, String>

class CourseTakingApplication(
    id: CourseTakingApplicationId,
    courseId: CourseId,
    status: Status
) {
    private val _id = id
    private val _courseId = courseId
    private var _status = status

    fun getId(): CourseTakingApplicationId {
        return _id
    }

    fun getCourseId(): CourseId {
        return _courseId
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