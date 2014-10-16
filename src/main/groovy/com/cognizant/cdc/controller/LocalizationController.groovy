package com.cognizant.cdc.controller


import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class LocalizationController {

    @RequestMapping(value="labels", method = RequestMethod.GET)
    @ResponseBody
    public Map localizedStrings(Locale locale) {
        ResourceBundle labels = ResourceBundle.getBundle("jobBundle",locale);

        Map result = [:];

        for(String key : labels.keys) {
            result.put(key, labels.getString(key))
        }

        result
    }
}
