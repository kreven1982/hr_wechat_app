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
    long createTime
	
    @Override
    Map toDBMap() {
        [
             _id : id,
             name : name,
             mobile : mobile,
             experience : experience,
             detail : detail,
             diploma : diploma.toString(),
             imgUrl : imgUrl,
             createTime : createTime
        ]
    }

    @Override
    public void fromDBMap(Map map) {
        this.id = map._id
        this.name = map.name
        this.mobile = map.mobile
        this.experience = map.experience
        this.detail = map.detail
        this.diploma = Diploma.valueOf(map.diploma)
        this.imgUrl = map.imgUrl
        this.createTime = map.createTime
    }
}
