package com.cognizant.cdc.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class SimpleJSPController {

    @RequestMapping(value="console")
    public String console() {
        return "console"
    }

    @RequestMapping(value="weixin")
    public String weixin() {
        return "weixin"
    }
}
