package com.cognizant.cdc.controller

import com.cognizant.cdc.model.Job
import com.cognizant.cdc.service.JobService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView
import com.cognizant.cdc.model.enums.Diploma
import com.cognizant.cdc.model.enums.RecruitmentType
import com.cognizant.cdc.model.vo.JobSearchResult
import com.cognizant.cdc.model.vo.JobSearchCriteria

@Controller
@RequestMapping(value = "job")
class JobController {

    @Autowired
    JobService jobService

    public static int PAGE_SIZE = 3

    @RequestMapping(value = "search", method = RequestMethod.GET)
    @ResponseBody
    public Map search(JobSearchCriteria jobSearchCriteria, @RequestParam(required = false, defaultValue = "1") Integer page) {

        JobSearchResult result = jobService.search(jobSearchCriteria, page, PAGE_SIZE)

        [
            total : result.total,
            jobs : result.jobs*.toRepresentationMap(),
            pageSize : PAGE_SIZE
        ]
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map jobs(@PathVariable("id") Integer jobId) {
        [result: jobService.getJob(jobId).toRepresentationMap()]
    }

    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    @ResponseBody
    public Map updateJob(@PathVariable("id") Integer jobId, @RequestBody Job job) {
        if(jobId == 0) {
            jobService.newJob(job)
        } else {
            jobService.updateJob(job)
        }
        return [ success: true ]
    }

    @RequestMapping(value = "{id}/activated", method = RequestMethod.POST)
    @ResponseBody
    public Map activateJob(@PathVariable("id") Integer jobId, @RequestParam(value= "activated") Boolean activated) {

        jobService.activateJob(jobId, activated)

        return [ success: true ]
    }

    @RequestMapping(value = "locations", method = RequestMethod.GET)
    @ResponseBody
    public Map getLocations() {

        return [ locations: [
                "上海", "北京", "深圳", "大连", "天津"
        ]]
    }

}
