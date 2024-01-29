package latestModel.useCase

import latestModel.dataStore.ApplicationsDataStore

fun cancelApplication(
    applicationId: String,
    applicationsDataStore: ApplicationsDataStore
){
    /*IO*/
    val application = applicationsDataStore.findById(applicationId)

    if(true){
        /*IO*/
        applicationsDataStore.delete(application.id)
    }
}