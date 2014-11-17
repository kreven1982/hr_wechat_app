package com.cognizant.cdc.model

class Application implements Entity<Application> {

    long jobId
    long profileId
    long time

    @Override
    Map toDBMap() {
        return [
            _id : [
                jobId : jobId,
                profileId : profileId
            ],
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
        this.time = map.time
    }

    @Override
    Map toRepresentationMap() {
        return [
             jobId: jobId,
             profileId: profileId,
             time: time
        ]
    }
}
