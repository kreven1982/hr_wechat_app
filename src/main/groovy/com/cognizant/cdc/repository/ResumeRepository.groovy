package com.cognizant.cdc.repository

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

import org.springframework.stereotype.Repository

import com.cognizant.cdc.model.Job
import com.cognizant.cdc.model.Resume
import com.cognizant.cdc.repository.support.BaseRepository
import com.mongodb.BasicDBObject
import com.mongodb.DBCollection
import com.mongodb.DBCursor
import com.mongodb.DBObject

@CompileStatic
@TypeChecked
@Repository
class ResumeRepository extends BaseRepository{

    public save(Resume resume) {
        DBCollection col = getCollection(DocumentNames.RESUME)
        col.save(new BasicDBObject(resume.toDBMap()))
    }

    public List<Resume> listAll() {
        DBCollection col = getCollection(DocumentNames.RESUME)
        DBCursor result = col.find();

        result.collect {
            DBObject record ->
                Resume resume = new Resume()
                resume.fromDBMap(record.toMap())
                resume
        }
    }
}
