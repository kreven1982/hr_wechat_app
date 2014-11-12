package com.cognizant.cdc.model

import com.cognizant.cdc.model.enums.Diploma
import com.cognizant.cdc.model.enums.RecruitmentType
import com.cognizant.cdc.util.Utils

class Job implements Entity<Job> {

    long id
    Long userId

    String title
    String content
    String introduction

    RecruitmentType type
    Diploma diploma

    List<String> locations

    Integer experienceFrom
    Integer experienceTo

    boolean activated = true
    int totalOfResumes = 0


    long createTime

    @Override
    public Map toDBMap() {

        Set<String> keywords = Utils.parseKeywords(title)
        keywords += Utils.parseKeywords(introduction)
        keywords += Utils.parseKeywords(Utils.html2text(content))

        return [
                _id: id,
                userId: userId,
                title : title,
                introduction: introduction,
                content: content,
                type: type.toString(),
                diploma: diploma.toString(),
                locations: locations,
                experienceFrom : experienceFrom,
                experienceTo : experienceTo,
                createTime : createTime,
                totalOfResumes : totalOfResumes,
                activated : activated,
                keywords : keywords
        ]
    }

    public Map toDBUpdateMap() {

        Map dataToUpdate = toDBMap()

        //should not override below fields
        dataToUpdate.remove("_id")
        dataToUpdate.remove("createTime")
        dataToUpdate.remove("totalOfResumes")
        dataToUpdate.remove("activated")

        dataToUpdate
    }

    @SuppressWarnings("GroovyAssignabilityCheck")
    @Override
    public Job fromDBMap(Map map) {
        if(map == null) {
            return null
        }

        this.id = map._id
        this.userId = map.userId
        this.title = map.title
        this.introduction = map.introduction
        this.content = map.content
        this.type = RecruitmentType.valueOf(map.type) as RecruitmentType
        this.diploma = Diploma.valueOf(map.diploma) as Diploma
        this.locations = map.locations
        this.experienceFrom = map.experienceFrom
        this.experienceTo = map.experienceTo
        this.totalOfResumes = map.totalOfResumes ?: 0
        this.createTime = map.createTime ?: 0
        this.activated = map.activated

        this
    }

    @Override
    Map toRepresentationMap() {
        return [
                id: id,
                userId: userId,
                title : title,
                introduction: introduction,
                content: content,
                type: type.toString(),
                diploma: diploma.toString(),
                locations: locations,
                experienceFrom : experienceFrom,
                experienceTo : experienceTo,
                totalOfResumes : totalOfResumes,
                createTime : createTime
        ]
    }
}
