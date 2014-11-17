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

    public void applyJob(long jobId, long profileId) {

        Application application = new Application([
                jobId : jobId,
                profileId : profileId,
                time : System.currentTimeMillis()
        ])

        boolean newApplication = applicationRepository.newApplication(application)

        if(newApplication) {
            jobRepository.increaseApplicationCount(jobId)
        }
    }
}
