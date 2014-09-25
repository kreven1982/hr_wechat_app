package com.cognizant.cdc.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping(value = "/template")
class templateController {

    @RequestMapping(value="{path}/{templateName}",method = RequestMethod.GET)
    public ModelAndView templates(@PathVariable String path, @PathVariable String templateName) {
        ModelAndView modelAndView = new ModelAndView(path + "/" + templateName)
        return modelAndView
    }
}
