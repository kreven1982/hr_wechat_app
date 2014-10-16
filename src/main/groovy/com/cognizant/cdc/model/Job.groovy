package com.cognizant.cdc.model

import com.cognizant.cdc.model.enums.Diploma
import com.cognizant.cdc.model.enums.RecruitmentType

class Job implements Entity<Job>{

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

    int totalOfResumes = 0
    long createTime

    @Override
    public Map toDBMap() {
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
                createTime : createTime
        ]
    }

    @SuppressWarnings("GroovyAssignabilityCheck")
    @Override
    public void fromDBMap(Map map) {
        this.id = map._id
        this.userId = map.userId
        this.title = map.title
        this.introduction= map.introduction
        this.content= map.content
        this.type= RecruitmentType.valueOf(map.type) as RecruitmentType
        this.diploma= Diploma.valueOf(map.diploma) as Diploma
        this.locations= map.locations
        this.experienceFrom = map.experienceFrom
        this.experienceTo = map.experienceTo
        this.totalOfResumes= map.totalOfResumes ?: 0
        this.createTime= map.createTime ?: 0
    }
}
