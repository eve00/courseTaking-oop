package data.repository

import domain.entity.Student

interface StudentsRepository {
    suspend fun findById(): Student
}