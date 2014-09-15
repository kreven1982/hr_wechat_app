package com.cognizant.cdc.repository

import com.mongodb.BasicDBObject
import com.mongodb.DB
import com.mongodb.DBCollection
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@CompileStatic
@TypeChecked
@Repository
class BaseRepository {

    @Autowired
    MongoFactory mongoFactory

    Map indexEnsured = [:]

    DBCollection getCollection(DocumentNames collectionName) {
        DB db = mongoFactory.getDB()

        DBCollection col = db.getCollectionFromString(collectionName.getName())
        if (col == null) {
            col = db.createCollection(collectionName.getName(), new BasicDBObject(collectionName.options))
        }

        if(!indexEnsured[collectionName]) {
            collectionName.indexConfigs?.each {
                List<Map> indexConfig ->

                Map index = indexConfig[0]
                Map indexOption = indexConfig[1]

                if(indexOption) {
                    col.ensureIndex(new BasicDBObject(index), new BasicDBObject(indexOption))
                } else {
                    col.ensureIndex(new BasicDBObject(index))
                }

            }

            indexEnsured[collectionName] = true
        }

        return col
    }
}
