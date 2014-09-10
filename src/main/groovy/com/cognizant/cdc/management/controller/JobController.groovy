package com.cognizant.cdc.management.controller

import com.cognizant.cdc.management.model.Job
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping(value = "/job")
class JobController {

    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView listJobs() {

        List<Job> jobs = [
             new Job(id: 1, title: "Bilingual IT IS Engineer", content: "Excellent English and Japanese skills; Have Knowledge on ticketing tools like Remedy, Siebel, Clarify, CA helpdesk, VNC, ControlF1, Dame Ware, Net meeting, Microsoft Share point etc.; Good communication skills and customer management experience; Troubleshooting skills on desktop, shrink-wrapped applications(MS office, Adobe etc.), VPN connectivity, dial up , wireless routers, etc."),
             new Job(id: 2, title: "Japanese .Net Developer", content: "Capability of reading Japanese requirement document, and writing test case in Japanese (JLPT 3+); Have knowledge on .Net(console application & windows form application) and at least 2-year experience on .Net development; Have business logic and passion on expanding insurance domain knowledge."),
             new Job(id: 3, title: "Perl/PHP Engineer", content: "Good English skills; At least 2 years of relevant work experience; Special list level in technology, PHP, PERL, CSS, JavaScript (jQuery), HTML; Pro-active problem solving abilities; Online development experience preferred; Experience and hands on Linux & Apache command and configuration will be a plus."),
             new Job(id: 4, title: "BI Business System Analyst", content: "Good English writing skill; 3 years ETL & BI reporting experience, especially in BI data modeling; 2 years of business system analyst experience in retail industry; an excellent grasp of database concepts and principles (i.e. transactional, analytical, reporting. Etc.)"),
             new Job(id: 5, title: "Cognos Engineer / Senior Engineer", content: "Good English writing skill; Experienced in Cognos Report Studio, Analysis Studio, Business Insight, Framework Manager and Transformer, SSIS; Solid knowledge of SQL."),
        ]

        ModelAndView modelAndView = new ModelAndView("jobList")
        modelAndView.addObject("jobs", jobs)

        return modelAndView
    }

    @RequestMapping(value="new", method=RequestMethod.POST)
    public String newJob() {
        return "newJob"
    }

    @RequestMapping(value="{pathName}")
    public String test(@PathVariable String pathName) {
           pathName
    }
}
