package com.cognizant.cdc.controller

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile

import com.cognizant.cdc.model.Resume
import com.cognizant.cdc.util.UUIDUtil
import com.fasterxml.jackson.databind.ObjectMapper

@Controller
@RequestMapping(value = "/resume")
class ResumeController {
	public static final String[] ACCEPTED_CONTENT_TYPE = [
		'image/jpeg',
		'image/gif',
		'image/png',
		'application/msword',
		'application/vnd.openxmlformats-officedocument.wordprocessingml.document'
		]
	
	public static final long MAX_UPLOAD_FILE_SIZE = 2048*1024 //2M
	
	@RequestMapping(value= "submit", method = RequestMethod.POST)
	@ResponseBody
	public Map submitResume(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "data") String data, HttpServletRequest request, HttpServletResponse response) {
		
		String uploadPath = request.getSession().getServletContext().getRealPath("upload")
		String fileName
		String fileURL
		if(file != null){
			boolean isAccepted = false
			String fileType = file.getContentType()
			for(it in ACCEPTED_CONTENT_TYPE){
				println it
				if(it == fileType){
					isAccepted = true
					break
				}
			}
			if(isAccepted){
				if(file.getSize() > MAX_UPLOAD_FILE_SIZE){
					return ['error':'附件尺寸不能大于2M']
				}
				try {
					fileName = file.getOriginalFilename()
					fileName = UUIDUtil.getUUID() + fileName.substring(fileName.lastIndexOf("."))
					println fileName
					
					def targetFile = new File(uploadPath, fileName)
					if(!targetFile.exists()) {
						targetFile.mkdirs()
					}
					file.transferTo(targetFile)
				} catch (Exception e) {
					e.printStackTrace()
					return ['error':'文件上传发生异常']
				}
				
				fileURL = request.getContextPath() + "/upload/" + fileName
				//println 'fileURL: ' + fileURL
				
			} else {
				return ['error':'附件类型只接受图片和word文档']
			}
		}
		
		//def map =  request.getParameterMap();
		//println data
		ObjectMapper mapper = new ObjectMapper()
		Resume resume = mapper.readValue(data, Resume.class)
		resume.imgUrl = fileURL
		
		return ["success" : true]
	}
}
