package com.cognizant.cdc.repository

import com.cognizant.cdc.model.Job
import com.cognizant.cdc.model.enums.Diploma
import com.cognizant.cdc.model.enums.RecruitmentType
import com.cognizant.cdc.model.vo.JobSearchResult
import com.cognizant.cdc.repository.support.BaseRepository
import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import com.mongodb.DBCursor
import com.mongodb.DBObject
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.springframework.stereotype.Repository

import com.cognizant.cdc.util.Utils

@CompileStatic
@TypeChecked
@Repository
class JobRepository extends BaseRepository{

    private static DBObject fields = new BasicDBObject([ keywords: 0 ])

    public save(Job job) {
        DBCollection col = getCollection(DocumentNames.JOB)
        col.save(new BasicDBObject(job.toDBMap()))
    }

    public update(Job job) {
        DBCollection col = getCollection(DocumentNames.JOB)
        BasicDBObject query = new BasicDBObject([_id: job.id])

        BasicDBObject update = new BasicDBObject([$set : job.toDBUpdateMap()])
        col.update(query, update)
    }

    public Job get(Integer id) {
        DBCollection col = getCollection(DocumentNames.JOB)
        BasicDBObject query = new BasicDBObject([_id: id])
        DBObject result = col.findOne(query, fields)

        if(result) {
            Job job = new Job()
            job.fromDBMap(result.toMap())
            return job
        }

        null
    }

    public int getTotal() {
        DBCollection col = getCollection(DocumentNames.JOB)
        col.count()
    }

    public void invalidateJob(long jobId) {
        DBCollection col = getCollection(DocumentNames.JOB)
        DBObject query = new BasicDBObject([_id: jobId])
        DBObject update = new BasicDBObject([ $set: [ invalid: true ] ])
        col.update(query, update)
    }

    /**
     * Important note:
     * Current, MongoDB 2.6 support text search, it works pretty good for latin characters, however, not support Chinese at all.
     *
     * To mitigate this, I implement text search using ANSJ lib to parse these words to a keyword field and search against it.
     * Refer to https://github.com/NLPchina/ansj_seg for details
     *
     *
     * Future evolving plan:
     *
     * 1) Use Solr/Lucene/ElasticSearch Solution
     * 2) Wait for chinese full text search in MongoDB
     */
    public JobSearchResult search(String keyword, RecruitmentType type, Integer experienceFrom, Integer experienceTo, Diploma diploma, String location, int page, int pageSize) {
        DBCollection col = getCollection(DocumentNames.JOB)

        int skip = (page - 1) * pageSize

        Map keywordQuery = keyword ? [
            keywords : [ $in: Utils.parseKeywords(keyword)]
        ] : [:]

        Map typeQuery = type ? [
            type : type.toString()
        ]: [:]

        Map fromQuery = experienceFrom ? [
            experienceFrom: [ $gte : experienceFrom ]
        ]: [:]

        Map toQuery = experienceTo ? [
            experienceTo  : [ $lte : experienceTo ]
        ]: [:]

        Map diplomaQuery = diploma ? [
             diploma : diploma.toString()
        ]: [:]

        Map locationQuery = location ? [
             locations : location
        ]: [:]

        Map queryMap = [:]

        queryMap.putAll(keywordQuery)
        queryMap.putAll(typeQuery)
        queryMap.putAll(fromQuery)
        queryMap.putAll(toQuery)
        queryMap.putAll(diplomaQuery)
        queryMap.putAll(locationQuery)

        DBObject query = new BasicDBObject(queryMap)
        DBCursor dbCursor = col.find(query, fields)

        JobSearchResult result = new JobSearchResult()
        result.total = dbCursor.count()
        result.jobs = dbCursor.skip(skip).limit(pageSize).collect {
            DBObject record ->
            Job job = new Job()
            job.fromDBMap(record.toMap())
            job
        }

        result
    }

    void applyJob(long jobId, long profileId) {
        DBCollection col = getCollection(DocumentNames.JOB)
        DBObject query = new BasicDBObject([ _id: jobId])
        DBObject update = new BasicDBObject([ $addToSet: [ profiles : profileId ]])

        //TODO not completed.
        col.findAndModify(query, update)
    }
}
