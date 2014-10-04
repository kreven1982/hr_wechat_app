package com.cognizant.cdc.service

import com.cognizant.cdc.model.Job
import com.cognizant.cdc.repository.JobRepository
import com.cognizant.cdc.repository.SequenceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class JobService {

    @Autowired
    JobRepository jobRepository

    @Autowired
    SequenceRepository sequenceRepository

    public Job getJob(id) {
        jobRepository.get(id)
    }

    public void newJob(Job job) {
        job.id = sequenceRepository.getNextID("JOB_SEQ")
        job.createTime = System.currentTimeMillis()
        jobRepository.save(job)
    }

    public void updateJob(Job job) {
        jobRepository.save(job);
    }

    public List<Job> listJobs() {
        jobRepository.list()
    }
}
