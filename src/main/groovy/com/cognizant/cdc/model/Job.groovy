package com.cognizant.cdc.model

import com.cognizant.cdc.model.enums.Diploma
import com.cognizant.cdc.model.enums.RecruitmentType

class Job implements Entity<Job>{

    long id
    long userId

    RecruitmentType type
    Diploma diploma

    List<String> locations

    Integer experienceFrom
    Integer experienceTo

    int totalOfResumes = 0

    String title
    String content

    @Override
    Map toDBMap() {
        return [
                id: id,
                userId: userId,
                type: type.toString(),
                diploma: diploma.toString(),

        ]
    }

    @Override
    Job fromDBMap(Map map) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    String getExperiences() {
        if(experienceFrom) {
            if(experienceTo) {
                return "$experienceFrom-$experienceTo"
            } else {
                return "$experienceFrom+"
            }
        } else {
            return null
        }
    }

    String getJobLocations() {
        locations?.join(",")
    }
}
