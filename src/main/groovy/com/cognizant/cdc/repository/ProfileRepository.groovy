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

import com.cognizant.cdc.model.vo.ProfileSearchResult
import com.cognizant.cdc.model.vo.ProfileSearchCriteria
import com.cognizant.cdc.util.Utils

@CompileStatic
@TypeChecked
@Repository
class ProfileRepository extends BaseRepository{

    public save(Profile profile) {
        DBCollection col = getCollection(DocumentNames.PROFILE)
        col.save(new BasicDBObject(profile.toDBMap()))
    }

    public Profile find(String name, String mobile) {
        DBCollection col = getCollection(DocumentNames.PROFILE)
        DBObject query = new BasicDBObject([ name: name, mobile: mobile ])
        DBObject result = col.findOne(query)

        result ? new Profile().fromDBMap(result.toMap()) : null
    }

    public List<Profile> getProfiles(List<Long> ids) {

        DBCollection col = getCollection(DocumentNames.PROFILE)
        DBObject query = new BasicDBObject([ _id: [ $in: ids ] ])
        DBCursor dbCursor = col.find(query)

        List<Profile> profiles = dbCursor.collect {
            DBObject dbObject ->

            new Profile().fromDBMap(dbObject.toMap())
        }

        profiles
    }

    ProfileSearchResult search(ProfileSearchCriteria profileSearchCriteria, Integer page, Integer pageSize) {
        DBCollection col = getCollection(DocumentNames.PROFILE)

        int skip = (page - 1) * pageSize

        Map nameQuery = profileSearchCriteria.name ? [
                name : profileSearchCriteria.name
        ]: [:]

        Map mobileQuery = profileSearchCriteria.mobile ? [
                mobile : profileSearchCriteria.mobile
        ]: [:]

        Map experienceQuery = profileSearchCriteria.experience ? [
                experience: profileSearchCriteria.experience
        ]: [:]

        Map diplomaQuery = profileSearchCriteria.diploma ? [
                diploma : profileSearchCriteria.diploma.toString()
        ]: [:]


        Map keywordQuery = profileSearchCriteria.keyword ? [
                keywords : [ $in: Utils.parseKeywords(profileSearchCriteria.keyword)]
        ] : [:]

        Map attachmentQuery = profileSearchCriteria.hasAttachmentOnly ? [
                attachmentId : [ $ne : null ]
        ] : [:]

        Map queryMap = [:]

        queryMap.putAll(nameQuery)
        queryMap.putAll(mobileQuery)
        queryMap.putAll(experienceQuery)
        queryMap.putAll(diplomaQuery)
        queryMap.putAll(keywordQuery)
        queryMap.putAll(attachmentQuery)

        DBObject query = new BasicDBObject(queryMap)
        DBObject sort = new BasicDBObject([ _id: -1 ])
        DBCursor dbCursor = col.find(query).sort(sort)

        ProfileSearchResult result = new ProfileSearchResult()
        result.total = dbCursor.count()
        result.profiles = dbCursor.skip(skip).limit(pageSize).collect {
            DBObject record ->
            new Profile().fromDBMap(record.toMap())
        }

        result
    }
}
