package com.cognizant.cdc.service

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import com.cognizant.cdc.repository.JobRepository
import com.cognizant.cdc.repository.ApplicationRepository
import com.cognizant.cdc.model.Application

@CompileStatic
@TypeChecked
@Service
class ApplicationService {


    @Autowired
    JobRepository jobRepository


    @Autowired
    ApplicationRepository applicationRepository

    public boolean newApplication(long jobId, long profileId) {

        Application application = applicationRepository.getApplication(jobId, profileId)

        if(application) {
            return false
        }

        application = new Application([
                jobId : jobId,
                profileId : profileId,
                rate : 0,
                comment: "",
                time : System.currentTimeMillis()
        ])

        applicationRepository.save(application)
        jobRepository.increaseApplicationCount(jobId)

        true
    }

    public void updateComment(long jobId, long profileId, String newComment) {
        applicationRepository.updateComment(jobId, profileId, newComment)
    }

    public void updateRate(long jobId, long profileId, int rate) {
        applicationRepository.updateRate(jobId, profileId, rate)
    }

    public List<Application> getApplications(long jobId) {
        applicationRepository.getApplications(jobId)
    }
}
