package com.cognizant.cdc.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.beans.factory.annotation.Autowired
import com.cognizant.cdc.service.ApplicationService
import org.springframework.web.bind.annotation.RequestBody


@Controller
@RequestMapping(value = "application")
class ApplicationController {


    @Autowired
    ApplicationService applicationService

    @RequestMapping(value = "{jobId},{profileId}/rate", method = RequestMethod.POST)
    @ResponseBody
    public Map updateRate(@PathVariable("jobId") Long jobId, @PathVariable("profileId") Long profileId, @RequestBody Map data) {

        if(!data.rate) {
            return [ result:  false]
        }

        applicationService.updateRate(jobId, profileId, data.rate as Integer)
        [result: true, message: "rate updated to " + data.rate]
    }

    @RequestMapping(value = "{jobId},{profileId}/comment", method = RequestMethod.GET)
    @ResponseBody
    public Map updateComment(@PathVariable("jobId") Long jobId, @PathVariable("profileId") Long profileId, @RequestBody Map data) {

        if(!data.comment) {
            return [ result:  false]
        }

        applicationService.updateComment(jobId, profileId, data.comment)
        [result: true, message: "comment updated to " + data.comment]
    }
}
