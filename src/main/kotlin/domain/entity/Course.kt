package domain.entity

import domain.entity.common.Identifier

typealias CourseId = Identifier<Course, String>


class Course(

    private val id: CourseId,
    private val name: String,
    private val term: String,
    private val dowAndPeriod: DowAndPeriod,
    private val max: Int,
    private val credit: Int
) {
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
        return max
    }

    fun getCredit():Int {
        return credit
    }
}

data class DowAndPeriod(
    val dow: String,
    val period: String
)