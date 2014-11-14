package com.cognizant.cdc.model.vo

import com.cognizant.cdc.model.enums.Diploma
import com.cognizant.cdc.model.enums.RecruitmentType
import groovy.transform.CompileStatic

@CompileStatic
class ProfileSearchCriteria {
    String name
    String mobile
    Diploma diploma
    String experience
    String keyword
}
