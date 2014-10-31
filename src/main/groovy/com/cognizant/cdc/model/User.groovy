package com.cognizant.cdc.model

class User implements Entity<User>{

    long id
    String userName
    String contact
    String password
    long createTime

    @Override
    public Map toDBMap() {
        [
                _id : id,
                userName : userName,
                contact: contact,
                password: password,
                createTime: createTime
        ]
    }

    @Override
    public void fromDBMap(Map map) {
        this.id = map._id
        this.userName = map.userName
        this.contact = map.contact
        this.password = map.password
        this.createTime = map.createTime
    }

    @Override
    Map toRepresentationMap() {
        [
                id : id,
                userName : userName,
                contact: contact,
                createTime: createTime
        ]
    }
}
