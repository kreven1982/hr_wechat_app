package com.cognizant.cdc.hr.model

class Job implements Entity<Job>{

    long id
    long userId

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
