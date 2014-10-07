package com.cognizant.cdc.repository

import com.cognizant.cdc.repository.support.BaseRepository
import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import com.mongodb.DBObject
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.springframework.stereotype.Repository

@CompileStatic
@TypeChecked
@Repository
class SequenceRepository extends BaseRepository {

    public long getNextID(String seqName) {
        DBCollection col = getCollection(DocumentNames.SEQUENCE)

        BasicDBObject query = new BasicDBObject(['_id': seqName])
        BasicDBObject update = new BasicDBObject(['$inc': ['value': 1]])

        DBObject ret = col.findAndModify(query, null, null, false, update, true, true)
        ret["value"] as long
    }
}