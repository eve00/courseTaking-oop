package domain.service

import domain.entity.Course
import domain.entity.CourseId
import domain.entity.StudentId


/*
* repository以上のものいらんかな
* */
interface CourseService {
    suspend fun getCourses(): List<Course>
    suspend fun getCourse(courseId: CourseId):Course
    }

