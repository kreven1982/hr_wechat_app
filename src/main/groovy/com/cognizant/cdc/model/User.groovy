package com.cognizant.cdc.model

class User implements Entity<User>{

    long id
    String name


    @Override
    Map toDBMap() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    User fromDBMap(Map map) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }
}
