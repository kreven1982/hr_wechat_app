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
@RequestMapping(value = "/management/job")
class JobManagementController {

    @Autowired
    JobService jobService

    @RequestMapping(value="template/{templateName}",method = RequestMethod.GET)
    public ModelAndView templates(@PathVariable String templateName) {
        ModelAndView modelAndView = new ModelAndView("management/" + templateName)
        return modelAndView
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView jobs(@RequestParam(value = "new", required = false) Boolean newFlag) {

        if(newFlag) {
            ModelAndView modelAndView = new ModelAndView("management/jobNew")
            modelAndView.addObject("job", new Job())
            return modelAndView

        } else {
            List<Job> jobs = jobService.listJobs();

            ModelAndView modelAndView = new ModelAndView("management/jobList")
            modelAndView.addObject("jobs", jobs)

            return modelAndView
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map newJob(@RequestBody Job job) {
        jobService.newJob(job)
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
