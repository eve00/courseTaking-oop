package domain.service

import domain.entity.CourseTakingApplication

interface CourseRegistrationService {

    fun drawing(courseTakingApplicationList: List<CourseTakingApplication>): List<CourseTakingApplication>

    fun registerMembers(courseTakingApplicationList: List<CourseTakingApplication>)
}