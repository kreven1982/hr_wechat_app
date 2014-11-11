package com.cognizant.cdc.service

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.cognizant.cdc.repository.ProfileRepository
import com.cognizant.cdc.repository.SequenceRepository
import com.cognizant.cdc.model.Profile

@CompileStatic
@TypeChecked
@Service
class ProfileService {
	@Autowired
	SequenceRepository sequenceRepository
	
	@Autowired
	ProfileRepository profileRepository
	
	public void newProfile(Profile profile) {
		profile.id = getNextProfileId()
		profile.createTime = System.currentTimeMillis()
		profileRepository.save(profile)
	}


    public List<Profile> listProfiles() {
		profileRepository.listAll()
	}


    private long getNextProfileId() {
        sequenceRepository.getNextID("PROFILE_SEQ")
    }
}
