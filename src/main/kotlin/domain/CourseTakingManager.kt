package domain

class CourseTakingManager {
    private val FirstServedState :Boolean = TODO("先着申請に空きがあるのかどうか")

    suspend fun applyCourseTaking(course:CourseId):Nothing = TODO("Async " +
            "userからidを取ってくる" +
            "CourseTakingApplicationを作成する（DBとの通信はListがする？）")

    fun cancenCourseTaking(courseTakingApplicationId: CourseTakingApplicationId):Nothing = TODO()
}