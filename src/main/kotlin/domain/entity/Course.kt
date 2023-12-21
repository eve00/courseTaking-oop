package domain.entity

import domain.entity.common.Identifier

typealias CourseId = Identifier<Course, String>


class Course(
    val id: CourseId,
    val name: String,
    val term: String,
    val dowAndPeriod: DowAndPeriod,
    val max: Int,
) {
    private var _max = max
    fun getId(): CourseId {
        return id
    }

    fun getName(): String {
        return name
    }

    fun getTerm(): String {
        return term
    }

    fun getDowAndPeriod(): DowAndPeriod {
        return dowAndPeriod
    }

    fun getMax(): Int {
        return _max
    }
}

data class DowAndPeriod(
    val dow: String,
    val period: String
)