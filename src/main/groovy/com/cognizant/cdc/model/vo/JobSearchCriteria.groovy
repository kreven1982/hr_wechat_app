package com.cognizant.cdc.model.vo

import org.springframework.web.bind.annotation.RequestParam
import com.cognizant.cdc.model.enums.RecruitmentType
import com.cognizant.cdc.model.enums.Diploma
import groovy.transform.CompileStatic

@CompileStatic
class JobSearchCriteria {
    String keyword
    RecruitmentType type
    Integer from
    Integer to
    Diploma diploma
    String location
    Boolean activated
}
