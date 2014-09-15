package com.cognizant.cdc.model

class Job implements Entity<Job>{

    long id
    long userId

    List<String> locations

    Integer experienceFrom
    Integer experienceTo

    int totalResume = 0

    String title
    String content

    @Override
    Map toDBMap() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
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
