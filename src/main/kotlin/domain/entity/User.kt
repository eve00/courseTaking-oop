package domain.entity

import domain.entity.common.Identifier

typealias StudentId = Identifier<Student, String>



sealed class User()
class Student(
    val id: StudentId,
    val name: String
): User() {
    fun getId(): StudentId {
        return id
    }
}
class Teacher(): User()