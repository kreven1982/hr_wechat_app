package com.cognizant.cdc.controller

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile

import com.cognizant.cdc.service.ProfileService
import com.cognizant.cdc.util.UUIDUtil
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.bind.annotation.PathVariable
import com.cognizant.cdc.model.Profile
import com.cognizant.cdc.service.AttachmentService
import org.apache.commons.io.IOUtils
import com.cognizant.cdc.util.Utils
import org.springframework.beans.factory.annotation.Value

@Controller
@RequestMapping(value = "profile")
class ProfileController {

	public static final String[] ACCEPTED_CONTENT_TYPE = [
		'image/jpeg',
		'image/gif',
		'image/png',
		'application/msword',
		'application/vnd.openxmlformats-officedocument.wordprocessingml.document'
		]

    @Value('${max.file.size}')
	private long MAX_UPLOAD_FILE_SIZE

	@Autowired
	ProfileService profileService

    @Autowired
    AttachmentService attachmentService


    @RequestMapping(value = "all", method = RequestMethod.GET)
    @ResponseBody
    public Map allProfiles() {
        [result: profileService.listProfiles()*.toRepresentationMap()]
    }

    final String EXCEED_MAXIMAL_FILE_SIZE = '附件尺寸不能大于3M'

	@RequestMapping(value="{jobId}", method = RequestMethod.POST)
	@ResponseBody
	public Map submit(@PathVariable String jobId, @RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "data") String data, HttpServletRequest request, HttpServletResponse response) {
		
        String attachmentId = null

		if(file != null){

			if(Utils.getContentType(Utils.getSuffix(file.originalFilename))){

			    if(file.getSize() > MAX_UPLOAD_FILE_SIZE) {

                    return ['error': EXCEED_MAXIMAL_FILE_SIZE]
				}

                byte[] fileContent = IOUtils.toByteArray(file.getInputStream())

                if(fileContent.size() > MAX_UPLOAD_FILE_SIZE) {
                    return ['error': EXCEED_MAXIMAL_FILE_SIZE]
                }

                attachmentId = attachmentService.newAttachment(file.originalFilename, fileContent)

			} else {
				return ['error':'附件类型只接受图片和word文档']
			}
		}
		
		ObjectMapper mapper = new ObjectMapper()
		Profile profile = mapper.readValue(data, Profile.class)
		profile.attachmentId = attachmentId

		profileService.newProfile(profile)

		return ["success" : true]
	}
}
