package com.cognizant.cdc.service

import com.cognizant.cdc.model.Job
import com.cognizant.cdc.model.vo.JobSearchResult
import com.cognizant.cdc.repository.JobRepository
import com.cognizant.cdc.repository.SequenceRepository
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.cognizant.cdc.model.enums.Diploma
import com.cognizant.cdc.model.enums.RecruitmentType

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

    public void applyJob(long jobId, long profileId) {
        jobRepository.applyJob(jobId, profileId)
    }

    public List<Job> listJobs(int page, int pageSize) {
        JobSearchResult jobSearchResult = jobRepository.search(null, null, null, null, null, null, page, pageSize)
        jobSearchResult.jobs
    }

    public int getTotal() {
        jobRepository.getTotal()
    }

    public JobSearchResult search(String keyword, RecruitmentType type, Integer experienceFrom, Integer experienceTo, Diploma diploma, String location) {

        jobRepository.search(keyword, type, experienceFrom, experienceTo, diploma, location, 1, 50)
    }
}
