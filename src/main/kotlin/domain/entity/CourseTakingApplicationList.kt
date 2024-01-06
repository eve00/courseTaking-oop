package domain.entity

class CourseTakingApplicationList(
    private val id: StudentId,
    private var courseTakingApplications: MutableList<CourseTakingApplication>,
    private var credits: Int
) {


    private val maxCredits: Int = 26
    fun getCourseTakingApplicationOfId(id: CourseTakingApplicationId): CourseTakingApplication? {
        return courseTakingApplications.find { courseTakingApplication ->
            courseTakingApplication.getId() == id
        }
    }

    fun addCourseTakingApplication(courseTakingApplication: CourseTakingApplication) {
        if (checkCanAdd(courseTakingApplication)){
            courseTakingApplications.add(courseTakingApplication)
            updateCredits()
        }else {
            throw IllegalStateException("取得可能な単位数を超過しています。")
        }
    }
    fun removeCourseTakingApplication(courseTakingApplicationId: CourseTakingApplicationId) {
            courseTakingApplications.remove(getCourseTakingApplicationOfId(courseTakingApplicationId))
            updateCredits()
    }

    private fun checkCanAdd(courseTakingApplication: CourseTakingApplication): Boolean {
        return maxCredits >= credits + courseTakingApplication.getCourse().getCredit()
    }

    private fun updateCredits() {
        credits = courseTakingApplications.map { courseTakingApplication ->
            courseTakingApplication.getCourse().getCredit()
        }.reduce { acc, credit -> acc + credit }
    }

    fun getCredits(): Int {
        return credits
    }
}

