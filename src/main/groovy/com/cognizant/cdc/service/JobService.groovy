package com.cognizant.cdc.service

import com.cognizant.cdc.model.Job
import com.cognizant.cdc.repository.JobRepository
import com.cognizant.cdc.repository.SequenceRepository
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@CompileStatic
@TypeChecked
@Service
class JobService {

    @Autowired
    JobRepository jobRepository

    @Autowired
    SequenceRepository sequenceRepository

    public Job getJob(Integer id) {
        jobRepository.get(id)
    }

    public void newJob(Job job) {
        job.id = sequenceRepository.getNextID("JOB_SEQ")
        job.createTime = System.currentTimeMillis()
        jobRepository.save(job)
    }

    public void updateJob(Job job) {
        jobRepository.update(job);
    }

    public void invalidateJob(long jobId) {
        jobRepository.invalidateJob(jobId)
    }

    public List<Job> listJobs(int page, int pageSize) {
        jobRepository.list(page, pageSize)
    }

    public int getTotal() {
        jobRepository.getTotal()
    }
}
