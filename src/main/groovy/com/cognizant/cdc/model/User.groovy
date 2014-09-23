package com.cognizant.cdc.model

class User implements Entity<User>{

    long id
    String name


    @Override
    public Map toDBMap() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void fromDBMap(Map map) {
    }
}
