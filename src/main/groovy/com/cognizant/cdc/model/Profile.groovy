package com.cognizant.cdc.model

import com.cognizant.cdc.model.enums.Diploma

class Profile implements Entity<Profile> {

	long id
	String name
	String mobile
	String experience
	String detail
	Diploma diploma
	String attachmentId
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
             attachmentId : attachmentId,
             createTime : createTime
        ]
    }

    @SuppressWarnings("GroovyAssignabilityCheck")
    @Override
    public Profile fromDBMap(Map map) {
        if(map == null) {
            return null
        }

        this.id = map._id
        this.name = map.name
        this.mobile = map.mobile
        this.experience = map.experience
        this.detail = map.detail
        this.diploma = Diploma.valueOf(map.diploma) as Diploma
        this.attachmentId = map.attachmentId
        this.createTime = map.createTime
        this
    }

    @Override
    Map toRepresentationMap() {
        [
                id : id,
                name : name,
                mobile : mobile,
                experience : experience,
                detail : detail,
                diploma : diploma.toString(),
                attachmentId : attachmentId,
                createTime : createTime
        ]
    }
}
