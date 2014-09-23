package com.cognizant.cdc.repository

import com.cognizant.cdc.model.Job
import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import com.mongodb.DBCursor
import com.mongodb.DBObject
import org.springframework.stereotype.Repository

@Repository
class JobRepository extends BaseRepository{

    public newJob(Job job) {
        DBCollection col = getCollection(DocumentNames.JOB)
        col.save(new BasicDBObject(job.toDBMap()))
    }

    public List<Job> listJobs() {
        DBCollection col = getCollection(DocumentNames.JOB)
        BasicDBObject sort = new BasicDBObject([_id: 1])
        DBCursor result = col.find().sort(sort);

        result.collect {
            DBObject record ->
                Job job = new Job()
                job.fromDBMap(record.toMap())
                job
        }
    }

}
