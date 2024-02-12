package domain

import domain.entity.*

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
        courseTakingApplications.add(courseTakingApplication)
        updateCredits()
    }

    fun removeCourseTakingApplication(courseTakingApplicationId: CourseTakingApplicationId) {
        courseTakingApplications.remove(getCourseTakingApplicationOfId(courseTakingApplicationId))
        updateCredits()
    }

    fun isWithinLimit(): Boolean {
        return maxCredits >= credits
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

