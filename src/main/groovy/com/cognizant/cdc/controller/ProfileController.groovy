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
import com.cognizant.cdc.service.JobService
import com.cognizant.cdc.model.enums.RecruitmentType
import com.cognizant.cdc.model.enums.Diploma
import com.cognizant.cdc.model.vo.JobSearchResult
import com.cognizant.cdc.model.vo.ProfileSearchResult
import com.cognizant.cdc.model.vo.ProfileSearchCriteria

@Controller
@RequestMapping(value = "profile")
class ProfileController {

    public static int PAGE_SIZE = 3

    @Value('${max.file.size}')
	private long MAX_UPLOAD_FILE_SIZE

    @Autowired
    JobService jobService

	@Autowired
	ProfileService profileService

    @Autowired
    AttachmentService attachmentService


    @RequestMapping(value = "all", method = RequestMethod.GET)
    @ResponseBody
    public Map allProfiles() {
        [result: profileService.listProfiles()*.toRepresentationMap()]
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    @ResponseBody
    public Map search(ProfileSearchCriteria profileSearchCriteria, @RequestParam(required = false, defaultValue = "1") Integer page) {

        ProfileSearchResult result = profileService.search(profileSearchCriteria, page, PAGE_SIZE)

        [
                total : result.total,
                jobs : result.profiles*.toRepresentationMap(),
                pageSize : PAGE_SIZE
        ]
    }

    final String EXCEED_MAXIMAL_FILE_SIZE = '附件尺寸不能大于3M'

	@RequestMapping(value="{jobId}", method = RequestMethod.POST)
	@ResponseBody
	public Map submit(@PathVariable Long jobId, @RequestParam(value = "file", required = false) MultipartFile file,
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

		long profileId = profileService.newProfile(profile)
        jobService.applyJob(jobId, profileId)

		return ["success" : true]
	}
}
