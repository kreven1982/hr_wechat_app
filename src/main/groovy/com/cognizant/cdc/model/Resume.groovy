package com.cognizant.cdc.model

import com.cognizant.cdc.model.enums.Diploma

class Resume implements Entity<Resume>{
	long id
	String name
	String mobile
	String experience
	String detail
	Diploma diploma
	String imgUrl
	
    @Override
    Map toDBMap() {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void fromDBMap(Map map) {
    }
}
