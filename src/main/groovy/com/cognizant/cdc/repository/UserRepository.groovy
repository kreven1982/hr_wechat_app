package com.cognizant.cdc.repository

import com.cognizant.cdc.model.User
import com.cognizant.cdc.repository.support.BaseRepository
import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import com.mongodb.DBObject
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.springframework.stereotype.Repository
import com.mongodb.DBCursor

@CompileStatic
@TypeChecked
@Repository
class UserRepository extends BaseRepository{

    public User getUserByToken(String token) {
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

    public void updateToken(String userName, String token) {
        DBCollection collection = getCollection(DocumentNames.USER)

        DBObject query = new BasicDBObject([ userName : userName ])
        DBObject update = new BasicDBObject([ $set: [ token: token ]])
        collection.update(query, update)
    }

    public void newUser(User user) {
        DBCollection collection = getCollection(DocumentNames.USER)
        DBObject insert = new BasicDBObject(user.toDBMap())
        collection.insert(insert)
    }

    public User getUserByName(String userName) {
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

    public void removeUser(long userId) {
        DBCollection collection = getCollection(DocumentNames.USER)
        DBObject toBeRemoved = new BasicDBObject([ _id: userId ])

        collection.remove(toBeRemoved)
    }

    public List<User> getUsers() {
        DBCollection collection = getCollection(DocumentNames.USER)
        DBCursor dbCursor = collection.find()

        dbCursor.collect {
            DBObject record ->
            new User().fromDBMap(record.toMap())
        }
    }
}
