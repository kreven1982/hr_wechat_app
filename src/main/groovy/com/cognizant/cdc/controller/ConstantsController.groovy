package com.cognizant.cdc.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import com.cognizant.cdc.model.enums.Diploma

@Controller
class ConstantsController {

    @RequestMapping(value="diplomas", method = RequestMethod.GET)
    @ResponseBody
    public Map getDiplomas() {
        [diplomas : Diploma.values()]
    }

}
