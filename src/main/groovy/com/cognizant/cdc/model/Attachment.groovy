package com.cognizant.cdc.model

import sun.reflect.generics.reflectiveObjects.NotImplementedException

class Attachment implements Entity<Attachment>{

    String id
    String originalName
    byte[] content

    long createTime
    long size

    @Override
    Map toDBMap() {
        return [
                _id: id,
                createTime: createTime,
                size: content.length,
                content: content,
                originalName : originalName
        ]
    }

    @Override
    void fromDBMap(Map map) {
        this.id = map._id
        this.createTime = map.createTime
        this.size = map.size
        this.content = map.content
        this.originalName = map.originalName
    }

    @Override
    Map toRepresentationMap() {
        throw new NotImplementedException()
    }
}
