package domain.entity

import domain.entity.common.Identifier

typealias CourseId = Identifier<Course, String>
class Course(
    val id: CourseId,
    val name: String,
    val capacity: Int

) {
    fun getId(): CourseId {return id}
    fun getName():String {return name}

    fun getCapacity():Int{return capacity}

    fun updateCapacity():Nothing = TODO("Async")

    suspend fun getDetail(): Nothing = TODO("Async")
}