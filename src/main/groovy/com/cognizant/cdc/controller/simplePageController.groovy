package com.cognizant.cdc.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class simplePageController {

    @RequestMapping(value="/console")
    public String console() {
        return "console"
    }
}
