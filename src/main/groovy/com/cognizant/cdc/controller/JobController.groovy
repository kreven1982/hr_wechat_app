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
import com.cognizant.cdc.service.ApplicationService
import com.cognizant.cdc.model.Application
import com.cognizant.cdc.model.Profile
import com.cognizant.cdc.service.ProfileService

@Controller
@RequestMapping(value = "job")
class JobController {

    @Autowired
    JobService jobService

    @Autowired
    ApplicationService applicationService

    @Autowired
    ProfileService profileService

    public static int PAGE_SIZE = 8

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
    public Map jobs(@PathVariable("id") Integer jobId, @RequestParam(required = false, defaultValue = "false") Boolean updateViewCount) {
        [result: jobService.getJob(jobId, updateViewCount).toRepresentationMap()]
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

    @RequestMapping(value = "{id}/applications", method = RequestMethod.GET)
    @ResponseBody
    public Map listApplications(@PathVariable("id") Integer jobId) {

        List<Application> applications = applicationService.getApplications(jobId)
        List<Profile> profiles = profileService.getProfiles(applications*.profileId)

        Map<Long, Profile> profileMap = [:]
        profiles.collect {
            Profile profile ->
            profileMap.put(profile.id, profile)
        }

        return [
                applications : applications,
                profiles : profileMap
        ]
    }

    @RequestMapping(value = "locations", method = RequestMethod.GET)
    @ResponseBody
    public Map getLocations() {

        return [ locations: [
                "上海", "北京", "深圳", "大连", "天津"
        ]]
    }

}
