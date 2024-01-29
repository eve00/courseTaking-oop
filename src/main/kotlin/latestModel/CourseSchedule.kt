package latestModel

import domain.entity.CourseId

class CourseSchedule(
    val courseManager: CourseManager,
    private val applications: List<Application>
) {
    private val MAX_CREDITS = 24

    private fun totalCredit(): Int = applications.filterNot { it.getStatus() == Status.REJECTED }.size

    private fun checkNotOver(): Boolean = totalCredit() <= MAX_CREDITS
    fun applyAsFirstService(student: Student, course: Course): Result<Unit> {
        if (courseManager.canApplyAsFirstserve()) {
            apply(student, course)
        }
        return Result.failure(Exception("満員"))
    }

    fun apply(student: Student, course: Course): Result<Unit> {
        val newApplication = createApplication(student, course)
        applications + newApplication

        if (checkNotOver()) {
            return Result.success(Unit)
        } else {
            return Result.failure(Exception("最大取得単位数を超過"))
        }
    }

    fun cancel(applicationId: String): Result<Unit> {
        val application = applications.find { it.id == applicationId }
            ?: return Result.failure(Exception("存在しないid"))

        if (application.getStatus() == Status.NOT_REGISTERED) {
            return Result.success(Unit)
        } else {
            return Result.failure(Exception("キャンセル不可"))
        }
    }
}


fun createApplication(student: Student, course: Course): Application {
    return Application("applicationId", student, course, Status.NOT_REGISTERED)
}

data class DowAndPeriod(
    val dow: Dow,
    val period: String
)

enum class Dow {
    Monday, Tuesday, Wednesday, Thursday, Friday
}