package com.cognizant.cdc.repository

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.springframework.stereotype.Repository
import com.cognizant.cdc.repository.support.BaseRepository
import com.mongodb.DBCollection
import com.mongodb.DBObject
import com.mongodb.BasicDBObject
import com.cognizant.cdc.model.Application
import com.mongodb.DBCursor

@CompileStatic
@TypeChecked
@Repository
class ApplicationRepository extends BaseRepository{

    /**
     * @param jobId
     * @param profileId
     * @return true : add new record, false : update existing record
     */
    public boolean newApplication(Application application) {
        DBCollection col = getCollection(DocumentNames.APPLICATION)
        DBObject query = new BasicDBObject([ _id: [ jobId: application.jobId,  profileId : application.profileId ] ] )
        DBObject update = new BasicDBObject([ time: application.time ])

        DBObject dbObject = col.findAndModify(query, null, null, false, update, false, true)

        boolean addNewRecord = dbObject == null
        addNewRecord
    }

    public List<Application> getApplications(long jobId) {

        DBCollection col = getCollection(DocumentNames.APPLICATION)
        DBObject query = new BasicDBObject([ "_id.jobId" : jobId ] )
        DBObject sort = new BasicDBObject([ time: -1 ])

        DBCursor dbCursor = col.find(query).sort(sort)

        List<Application> applications = dbCursor.collect {
            DBObject dbObject ->
            new Application().fromDBMap(dbObject.toMap())
        }

        applications
    }

}
