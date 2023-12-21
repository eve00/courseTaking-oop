package domain.entity

import domain.entity.common.Identifier

typealias CourseId = Identifier<Course, String>
class Course(
    val id: CourseId,
    val name: String,
    val capacity: Int,
    val term :String,
    val dow: String,
    val period: String
) {
    private var _capacity = capacity
    fun getId(): CourseId {return id}
    fun getName():String {return name}
    fun getCapacity():Int{return capacity}
    fun updateCapacity(capacity: Int){
        _capacity = capacity
    }
    suspend fun getDetail(): Nothing = TODO("Async")
}
