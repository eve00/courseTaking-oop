package data.repository

import domain.entity.Student
import domain.entity.StudentId

interface StudentsRepository {
    suspend fun findAll(): List<Student>
    suspend fun findById(studentId: StudentId): Student
}

class StudentsRepositoryImpl : StudentsRepository {
    override suspend fun findAll(): List<Student> {
        TODO("Not yet implemented")
    }

    override suspend fun findById(studentId: StudentId): Student {
        TODO("Not yet implemented")
    }
}
