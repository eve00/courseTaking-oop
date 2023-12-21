package data.repository

import domain.entity.Student
import domain.entity.StudentId

interface StudentsRepository {
    suspend fun findAll(): List<Student>
    suspend fun findById(studentId: StudentId): Student
}