package com.cognizant.cdc.management.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
@RequestMapping(value = "job")
class JobController {

    @RequestMapping(method=RequestMethod.GET)
    public String listJobs() {
        return "jobList"
    }


    @RequestMapping(value="new", method=RequestMethod.POST)
    public String newJob() {
        return "newJob"
    }
}
