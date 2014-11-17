package com.cognizant.cdc.model

class User implements Entity<User> {

    long id
    String userName
    String contact
    String password
    Boolean isAdmin
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
    public User fromDBMap(Map map) {
        if(map == null) {
            return null
        }

        this.id = map._id
        this.userName = map.userName
        this.contact = map.contact
        this.password = map.password
        this.createTime = map.createTime
        this.isAdmin = map.isAdmin

        this
    }

    @Override
    Map toRepresentationMap() {
        [
                id : id,
                userName : userName,
                contact: contact,
                createTime: createTime,
                isAdmin : isAdmin
        ]
    }
}
