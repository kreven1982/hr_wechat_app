package com.cognizant.cdc.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestBody
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.Cookie

@Controller
@RequestMapping(value="user")
class UserController {
    
    final static int MAX_COOKIE_AGE = -1
    
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Map login(@RequestBody Map request, HttpServletResponse response) {

        String token = "some token"
        Cookie cookie = new Cookie("token", token)
        cookie.setMaxAge(MAX_COOKIE_AGE)
        cookie.setPath("/")
        cookie.setSecure(true)

        response.addCookie(cookie)

        [result : true]
    }

}
