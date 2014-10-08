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

@Controller
@RequestMapping(value = "/job")
class JobController {

    @Autowired
    JobService jobService

    public static int PAGE_SIZE = 15

    @RequestMapping(value = "all", method = RequestMethod.GET)
    @ResponseBody
    public Map allJobs(@RequestParam ("page") Integer page) {

        [
                result: jobService.listJobs(page ?: 1, PAGE_SIZE),
                total : jobService.getTotal(),
                pageSize : PAGE_SIZE
        ]
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Map jobs(@PathVariable("id") Integer jobId) {
        [result: jobService.getJob(jobId)]
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

    @RequestMapping(value = "locations", method = RequestMethod.GET)
    @ResponseBody
    public Map getLocations() {

        return [ locations: [
                "上海", "北京", "深圳", "大连", "天津"
        ]]
    }
}
