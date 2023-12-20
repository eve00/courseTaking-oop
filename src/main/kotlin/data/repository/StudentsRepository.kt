package data.repository

interface StudentsRepository {
    suspend fun findById()
}