package com.cognizant.cdc.service

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.cognizant.cdc.repository.ProfileRepository
import com.cognizant.cdc.repository.SequenceRepository
import com.cognizant.cdc.model.Profile
import com.cognizant.cdc.model.vo.ProfileSearchResult
import com.cognizant.cdc.model.enums.Diploma
import com.cognizant.cdc.model.vo.ProfileSearchCriteria

@CompileStatic
@TypeChecked
@Service
class ProfileService {
	@Autowired
	SequenceRepository sequenceRepository
	
	@Autowired
	ProfileRepository profileRepository
	
	public long newProfile(Profile profile) {
		profile.id = getNextProfileId()
		profile.createTime = System.currentTimeMillis()
		profileRepository.save(profile)

        profile.id
	}

    public Profile find(String name, String mobile) {
        profileRepository.find(name, mobile)
    }


    private long getNextProfileId() {
        sequenceRepository.getNextID("PROFILE_SEQ")
    }

    ProfileSearchResult search(ProfileSearchCriteria profileSearchCriteria, Integer page, Integer pageSize) {
        profileRepository.search(profileSearchCriteria, page, pageSize)
    }
}
