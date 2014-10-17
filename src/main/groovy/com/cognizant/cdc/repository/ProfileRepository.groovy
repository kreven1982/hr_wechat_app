package com.cognizant.cdc.repository

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

import org.springframework.stereotype.Repository

import com.cognizant.cdc.model.Profile
import com.cognizant.cdc.repository.support.BaseRepository
import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import com.mongodb.DBCursor
import com.mongodb.DBObject

@CompileStatic
@TypeChecked
@Repository
class ProfileRepository extends BaseRepository{

    public save(Profile profile) {
        DBCollection col = getCollection(DocumentNames.PROFILE)
        col.save(new BasicDBObject(profile.toDBMap()))
    }

    public List<Profile> listAll() {
        DBCollection col = getCollection(DocumentNames.PROFILE)
        DBCursor result = col.find();

        result.collect {
            DBObject record ->
                Profile profile = new Profile()
                profile.fromDBMap(record.toMap())
                profile
        }
    }
}
