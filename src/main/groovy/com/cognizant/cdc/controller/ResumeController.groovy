package com.cognizant.cdc.controller

import com.cognizant.cdc.model.Resume
import com.cognizant.cdc.model.enums.Diploma
import com.cognizant.cdc.util.UUIDUtil
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

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


    List<Resume> exampls =  [
            new Resume(id:1, name:'张三', mobile:'18812345678', diploma:Diploma.associate, experience:'2-3', detail:'熟悉J2EE，HTML，CSS，JavaScript等技术', imgUrl:"http://localhost:8080/ROOT/hm-smac-1.jpg"),
            new Resume(id:2, name:'李四', mobile:'18812345678', diploma:Diploma.master, experience:'5-7', detail:'DBA专家', imgUrl:"http://localhost:8080/ROOT/hm-smac-1.jpg"),
            new Resume(id:3, name:'王五', mobile:'18812345678', diploma:Diploma.doctor, experience:'5-7', detail:'Senior Architecture', imgUrl:"http://localhost:8080/ROOT/hm-smac-1.jpg"),
            new Resume(id:4, name:'Henry Li', mobile:'18812345678', diploma:Diploma.master, experience:'5-7', detail:'Senior Architecture', imgUrl:"http://localhost:8080/ROOT/hm-smac-1.jpg"),
            new Resume(id:5, name:'Sharwn Li', mobile:'18812345678', diploma:Diploma.master, experience:'5-7', detail:'Senior Architecture', imgUrl:"http://localhost:8080/ROOT/hm-smac-1.jpg"),
            new Resume(id:6, name:'Teddy Li', mobile:'18812345678', diploma:Diploma.doctor, experience:'5-7', detail:'Senior Architecture', imgUrl:"http://localhost:8080/ROOT/hm-smac-1.jpg"),
            new Resume(id:7, name:'Mary Li', mobile:'18812345678', diploma:Diploma.doctor, experience:'5-7', detail:'Fluent in Japanese and English skills; Extensive Clinical Data Management background/experience required; RAVE experience is a must; Detailed understanding of Electronic Data Capture (EDC) concepts and techniques and of clinical trial principles is required; Must have hands-on experience with clinical data querying, issues resolution, and coordination of CDM activities; Experience working in a clinical development environment is required; Solid understanding of the implications of applicable laws and regulations governing Clinical Research.', imgUrl:"http://localhost:8080/ROOT/hm-smac-1.jpg"),
    ]

    @RequestMapping(value = "all", method = RequestMethod.GET)
    @ResponseBody
    public Map allResumes() {
        [result: exampls]
    }

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
