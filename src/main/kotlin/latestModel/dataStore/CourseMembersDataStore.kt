package latestModel.dataStore

import latestModel.Student


interface CourseMembersDataStore {

    fun save(members:List<Student>)
}