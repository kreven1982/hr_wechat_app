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
    public void save(Application application) {

        DBCollection col = getCollection(DocumentNames.APPLICATION)
        DBObject save = new BasicDBObject(application.toDBMap())

        col.save(save)
    }

    public void updateComment(long jobId, long profileId, String newComment) {
        DBCollection col = getCollection(DocumentNames.APPLICATION)
        DBObject query = new BasicDBObject([ _id: [ jobId: jobId,  profileId : profileId ] ] )
        DBObject update = new BasicDBObject([ $set : [ comment: newComment ] ])
        col.update(query, update)
    }

    public void updateRate(long jobId, long profileId, int rate) {
        DBCollection col = getCollection(DocumentNames.APPLICATION)
        DBObject query = new BasicDBObject([ _id: [ jobId: jobId,  profileId : profileId ] ] )
        DBObject update = new BasicDBObject([ $set : [ rate: rate ] ])
        col.update(query, update)
    }

    public Application getApplication(long jobId, profileId) {
        DBCollection col = getCollection(DocumentNames.APPLICATION)
        DBObject query = new BasicDBObject([ _id: [ jobId: jobId,  profileId : profileId ] ] )

        DBObject result = col.findOne(query)

        result ? new Application().fromDBMap(result.toMap()) : null
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
