package domain

typealias CourseTakingApplicationId = Identifier<CourseTakingApplication, String>

data class CourseTakingApplication(
    val id: CourseTakingApplicationId,
    val courseId: String,
    val Status: Status
)

enum class Status{
    UNCONFIRMED,CONFIRMED
}