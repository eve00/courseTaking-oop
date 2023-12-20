package data.repository

interface CoursesRepository {
    suspend fun findAll()
}