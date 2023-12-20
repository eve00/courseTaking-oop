package data.repository

interface CourseTakingApplicationsHistoryRepository {
    suspend fun findAll()
    suspend fun delete()
    suspend fun save()
}