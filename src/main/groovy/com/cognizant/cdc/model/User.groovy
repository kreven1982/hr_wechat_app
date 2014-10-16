package com.cognizant.cdc.model

import com.cognizant.cdc.model.enums.Diploma

class User implements Entity<User>{

    long id
    String userName
    String contact
    long createTime

    @Override
    public Map toDBMap() {
        [
                _id : id,
                userName : userName,
                contact: contact,
                createTime: createTime
        ]
    }

    @Override
    public void fromDBMap(Map map) {
        this.id = map._id
        this.userName = map.userName
        this.contact = map.contact
        this.createTime = map.createTime
    }
}
