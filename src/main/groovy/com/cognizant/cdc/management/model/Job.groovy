package com.cognizant.cdc.management.model

class Job implements Entity<Job>{

    long id
    long userId

    Integer ExperienceFrom
    Integer ExperienceTo

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
}
