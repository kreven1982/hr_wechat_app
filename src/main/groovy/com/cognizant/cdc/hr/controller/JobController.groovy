package com.cognizant.cdc.hr.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.http.HttpMethod
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class JobController {

    @RequestMapping(value = "{pathName}")
    public String simpleView(@PathVariable String pathName) {
        return pathName
    }

}
