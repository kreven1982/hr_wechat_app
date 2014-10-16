package com.cognizant.cdc.repository

import com.cognizant.cdc.model.User
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
class UserRepository extends BaseRepository{

    User getUserByToken(String token) {
        DBCollection collection = getCollection(DocumentNames.USER)

        DBObject query = new BasicDBObject([ token: token ])
        DBObject result = collection.findOne(query)

        if(!result) {
            return null
        }

        User user = new User()
        user.fromDBMap(result.toMap())
        user
    }

    void updateToken(long userId, String token) {
        DBCollection collection = getCollection(DocumentNames.USER)

        DBObject query = new BasicDBObject([ _id : userId ])
        DBObject update = new BasicDBObject([ $set: [ token: token ]])
        collection.update(query, update)
    }

    void newUser(User user) {
        DBCollection collection = getCollection(DocumentNames.USER)
        DBObject insert = new BasicDBObject(user.toDBMap())
        collection.insert(insert)
    }

    User getUserByName(String userName) {
        DBCollection collection = getCollection(DocumentNames.USER)

        DBObject query = new BasicDBObject([ userName: userName ])
        DBObject result = collection.findOne(query)

        if(!result) {
            return null
        }


        User user = new User()
        user.fromDBMap(result.toMap())
        user
    }
}
