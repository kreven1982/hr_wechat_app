package com.cognizant.cdc.model

import com.cognizant.cdc.model.enums.Diploma

class User implements Entity<User>{

    long id
    String name
    String mobile
    String experience
    Diploma diploma
    String introduction

    @Override
    public Map toDBMap() {
        [:]
    }

    @Override
    public void fromDBMap(Map map) {
    }
}
