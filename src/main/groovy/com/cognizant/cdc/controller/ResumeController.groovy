package com.cognizant.cdc.controller

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile

import com.cognizant.cdc.model.Resume

@Controller
@RequestMapping(value = "/resume")
class ResumeController {

    @RequestMapping(value= "submit", method = RequestMethod.POST)
    @ResponseBody
	public Map submitResume(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam(value = "data") String data, HttpServletRequest request, HttpServletResponse response) {
		if(file != null){
			println "upload file: " + file.getOriginalFilename()
		}
		//def map =  request.getParameterMap();
		println data
		
        return ["success" : true]
    }

}
