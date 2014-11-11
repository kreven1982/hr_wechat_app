package com.cognizant.cdc.repository

import org.springframework.stereotype.Repository
import com.cognizant.cdc.repository.support.BaseRepository
import com.mongodb.DBCollection
import com.cognizant.cdc.model.Attachment
import com.mongodb.DBObject
import com.mongodb.BasicDBObject

/**
 * Comparing putting files into file system, the overall solution to put binary file in a MongoDB document is fine.
 * MongoDB document allows 16mb maximal, and we already limit attachment to be less than 3mb.
 *
 * However, the only concern for this solution is that we now can not STREAMING it unless we use GridFS, this should be fine for this small
 * hr application, but definitely not suitable for large scale applications.
 */
@Repository
class AttachmentRepository extends BaseRepository{

    public void save(Attachment attachment) {

        DBCollection collection = getCollection(DocumentNames.ATTACHMENT)
        DBObject toSave = new BasicDBObject(attachment.toDBMap())

        collection.save(toSave)
    }


    public Attachment get(String id) {
        DBCollection collection = getCollection(DocumentNames.ATTACHMENT)
        DBObject query = new BasicDBObject([ _id : id ])
        DBObject result = collection.findOne(query)

        Attachment attachment = null
        if(result) {
            attachment = new Attachment()
            attachment.fromDBMap(result.toMap())
        }

        attachment
    }


    public void delete(String id) {
        DBCollection collection = getCollection(DocumentNames.ATTACHMENT)
        DBObject query = new BasicDBObject([ _id : id ])
        collection.remove(query)
    }
}
