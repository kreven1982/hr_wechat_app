package com.cognizant.cdc.controller

import com.cognizant.cdc.model.Job
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping(value = "/job")
class JobController {

    List<Job> sampleJobs = [
            new Job(id: 1, locations: ["Shanghai"], experienceFrom: 1, experienceTo: 5, title: "Bilingual IT IS Engineer", content: "Excellent English and Japanese skills; Have Knowledge on ticketing tools like Remedy, Siebel, Clarify, CA helpdesk, VNC, ControlF1, Dame Ware, Net meeting, Microsoft Share point etc.; Good communication skills and customer management experience; Troubleshooting skills on desktop, shrink-wrapped applications(MS office, Adobe etc.), VPN connectivity, dial up , wireless routers, etc."),
            new Job(id: 2, locations: ["Shanghai","Shenzhen"],experienceFrom: 3,title: "Japanese .Net Developer", content: "Capability of reading Japanese requirement document, and writing test case in Japanese (JLPT 3+); Have knowledge on .Net(console application & windows form application) and at least 2-year experience on .Net development; Have business logic and passion on expanding insurance domain knowledge."),
            new Job(id: 3, locations: ["天津","深圳"],experienceFrom: 3, experienceTo: 5, title: "Perl/PHP Engineer", content: "Good English skills; At least 2 years of relevant work experience; Special list level in technology, PHP, PERL, CSS, JavaScript (jQuery), HTML; Pro-active problem solving abilities; Online development experience preferred; Experience and hands on Linux & Apache command and configuration will be a plus."),
            new Job(id: 4, locations: ["Shanghai"],experienceFrom: 6, title: "BI Business System Analyst", content: "Good English writing skill; 3 years ETL & BI reporting experience, especially in BI data modeling; 2 years of business system analyst experience in retail industry; an excellent grasp of database concepts and principles (i.e. transactional, analytical, reporting. Etc.)"),
            new Job(id: 5, locations: ["Shanghai","Tianjing","Shenzhen"],experienceFrom: 3, experienceTo: 8, title: "Cognos Engineer / Senior Engineer", content: "Good English writing skill;\nExperienced in Cognos Report Studio, Analysis Studio, Business Insight, Framework Manager and Transformer, SSIS;\nSolid knowledge of SQL."),
            new Job(id: 6, locations: ["Shanghai"],experienceFrom: 3, experienceTo: 8, title: "Android/Hybrid Bridge Engineer", content: "Fluent Korean and English communication skills;  6-8+ years of experience with Andorid, Java, JavaScript (HTML5, JQuery mobile, Webservices, phonegap will be a plus)."),
            new Job(id: 7, locations: ["上海"], experienceFrom: 5, title: "Sr. Clinical Data Analyst/Manager", content: "Fluent in Japanese and English skills; Extensive Clinical Data Management background/experience required; RAVE experience is a must; Detailed understanding of Electronic Data Capture (EDC) concepts and techniques and of clinical trial principles is required; Must have hands-on experience with clinical data querying, issues resolution, and coordination of CDM activities; Experience working in a clinical development environment is required; Solid understanding of the implications of applicable laws and regulations governing Clinical Research."),
    ]

    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView listJobs() {

        List<Job> jobs = sampleJobs;

        ModelAndView modelAndView = new ModelAndView("weixin/jobList")
        modelAndView.addObject("jobs", jobs)

        return modelAndView
    }

    @RequestMapping(value= "apply", method = RequestMethod.GET)
    public String showApplyJobPage() {
        return "weixin/applyJob"
    }

}
