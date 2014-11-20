package com.cognizant.cdc.model

class Application implements Entity<Application> {

    long jobId
    long profileId
    int rate
    String comment
    long time

    @Override
    Map toDBMap() {
        return [
            _id : [
                jobId : jobId,
                profileId : profileId
            ],
            rate : rate,
            comment: comment,
            time : time
        ]
    }

    @Override
    Application fromDBMap(Map map) {
        if(!map) {
            return null
        }

        this.jobId = map._id?.jobId
        this.profileId = map._id?.profileId
        this.rate = map.rate ?: 0
        this.comment = map.comment
        this.time = map.time

        this
    }

    @Override
    Map toRepresentationMap() {
        return [
             jobId: jobId,
             profileId: profileId,
             rate: rate,
             comment: comment,
             time: time
        ]
    }
}
