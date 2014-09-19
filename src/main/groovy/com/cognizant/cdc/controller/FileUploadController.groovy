package com.cognizant.cdc.controller

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile

@Controller
class FileUploadController {
	
	@RequestMapping(value = "/upload")
	public @ResponseBody Map upload(@RequestParam(value = "contentImg") MultipartFile file, HttpServletRequest request,HttpServletResponse response){
		String path = request.getSession().getServletContext().getRealPath("upload")
		String fileName = file.getOriginalFilename()
		try {	
			def targetFile = new File(path, fileName)
			if(!targetFile.exists()){
				targetFile.mkdirs()
			}
			file.transferTo(targetFile)
		} catch (Exception e) {
			e.printStackTrace()
			return ["success": false]
		}
		
		return ["success": true, "file_path": request.getContextPath()+"/upload/"+fileName]
	}
}
