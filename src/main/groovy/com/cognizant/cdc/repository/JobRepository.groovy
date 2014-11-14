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
import com.cognizant.cdc.model.vo.JobSearchCriteria

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
    public JobSearchResult search(JobSearchCriteria jobSearchCriteria, int page, int pageSize) {
        DBCollection col = getCollection(DocumentNames.JOB)

        int skip = (page - 1) * pageSize

        Map keywordQuery = jobSearchCriteria.keyword ? [
            keywords : [ $in: Utils.parseKeywords(jobSearchCriteria.keyword)]
        ] : [:]

        Map typeQuery = jobSearchCriteria.type ? [
            type : jobSearchCriteria.type.toString()
        ]: [:]

        Map fromQuery = jobSearchCriteria.from ? [
            experienceFrom: [ $gte : jobSearchCriteria.from ]
        ]: [:]

        Map toQuery = jobSearchCriteria.to ? [
            experienceTo  : [ $lte : jobSearchCriteria.to ]
        ]: [:]

        Map diplomaQuery = jobSearchCriteria.diploma ? [
             diploma : jobSearchCriteria.diploma.toString()
        ]: [:]

        Map locationQuery = jobSearchCriteria.location ? [
             locations : jobSearchCriteria.location
        ]: [:]

        Map queryMap = [:]

        queryMap.putAll(keywordQuery)
        queryMap.putAll(typeQuery)
        queryMap.putAll(fromQuery)
        queryMap.putAll(toQuery)
        queryMap.putAll(diplomaQuery)
        queryMap.putAll(locationQuery)

        DBObject query = new BasicDBObject(queryMap)
        DBObject sort = new BasicDBObject([ _id: -1 ])
        DBCursor dbCursor = col.find(query, fields).sort(sort)

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
