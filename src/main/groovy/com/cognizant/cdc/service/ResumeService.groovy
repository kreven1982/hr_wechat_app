package com.cognizant.cdc.service

import groovy.transform.CompileStatic
import groovy.transform.TypeChecked

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import com.cognizant.cdc.model.Resume
import com.cognizant.cdc.repository.ResumeRepository
import com.cognizant.cdc.repository.SequenceRepository

@CompileStatic
@TypeChecked
@Service
class ResumeService {
	@Autowired
	SequenceRepository sequenceRepository
	
	@Autowired
	ResumeRepository resumeRepository
	
	public void newResume(Resume resume) {
		resume.id = sequenceRepository.getNextID("RESUME_SEQ")
		resume.createTime = System.currentTimeMillis()
		resumeRepository.save(resume)
	}
	
	public List<Resume> listResumes() {
		resumeRepository.listAll()
	}
}
