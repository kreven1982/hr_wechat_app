package com.cognizant.cdc.hr.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.http.HttpMethod
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

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
