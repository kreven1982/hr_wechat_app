package com.cognizant.cdc.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.cdc.model.Resume

@Controller
@RequestMapping(value = "/management/resume")
class ResumeManagementController {
	
	List<Resume> exampls =  [
		new Resume(id:1, name:'张三', mobile:'18812345678', experience:'2-3', detail:'技术不错'),
		new Resume(id:2, name:'李四', mobile:'18812345678', experience:'5-7', detail:'Java专家'),
		new Resume(id:3, name:'王五', mobile:'18812345678', experience:'5-7', detail:'Senior Architecture'),
		new Resume(id:4, name:'Henry Li', mobile:'18812345678', experience:'5-7', detail:'Senior Architecture'),
		new Resume(id:5, name:'Sharwn Li', mobile:'18812345678', experience:'5-7', detail:'Senior Architecture'),
		new Resume(id:6, name:'Teddy Li', mobile:'18812345678', experience:'5-7', detail:'Senior Architecture'),
		new Resume(id:7, name:'Mary Li', mobile:'18812345678', experience:'5-7', detail:'Fluent in Japanese and English skills; Extensive Clinical Data Management background/experience required; RAVE experience is a must; Detailed understanding of Electronic Data Capture (EDC) concepts and techniques and of clinical trial principles is required; Must have hands-on experience with clinical data querying, issues resolution, and coordination of CDM activities; Experience working in a clinical development environment is required; Solid understanding of the implications of applicable laws and regulations governing Clinical Research.'),
		]

    @RequestMapping(value= "search", method=RequestMethod.GET)
    public String search() {
		return "management/resumeSearch"
    }

    @RequestMapping(value= "list", method = RequestMethod.GET)
    public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("management/resumeList") 
		modelAndView.addObject("resumeList", exampls)
		
        return modelAndView
    }

}
