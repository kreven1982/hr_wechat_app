package com.cognizant.cdc.repository

import com.cognizant.cdc.model.Job
import com.cognizant.cdc.repository.support.BaseRepository
import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import com.mongodb.DBCursor
import com.mongodb.DBObject
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.springframework.stereotype.Repository

@CompileStatic
@TypeChecked
@Repository
class JobRepository extends BaseRepository{

    public save(Job job) {
        DBCollection col = getCollection(DocumentNames.JOB)
        col.save(new BasicDBObject(job.toDBMap()))
    }

    public update(Job job) {
        DBCollection col = getCollection(DocumentNames.JOB)
        BasicDBObject query = new BasicDBObject([_id: job.id])
        Map dataToUpdate = job.toDBMap()

        dataToUpdate.remove("_id")
        dataToUpdate.remove("createTime")

        BasicDBObject update = new BasicDBObject([$set : dataToUpdate])
        col.update(query, update)
    }

    public List<Job> list(int page, int pageSize) {
        DBCollection col = getCollection(DocumentNames.JOB)
        BasicDBObject sort = new BasicDBObject([_id: -1])
        int skip = (page - 1) * pageSize
        DBCursor result = col.find().sort(sort).skip(skip).limit(pageSize);

        result.collect {
            DBObject record ->
                Job job = new Job()
                job.fromDBMap(record.toMap())
                job
        }
    }

    Job get(Integer id) {
        DBCollection col = getCollection(DocumentNames.JOB)
        BasicDBObject query = new BasicDBObject([_id: id])
        DBObject result = col.findOne(query)

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
}
