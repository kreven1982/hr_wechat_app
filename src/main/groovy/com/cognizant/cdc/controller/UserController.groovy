package com.cognizant.cdc.controller

import com.cognizant.cdc.model.User
import com.cognizant.cdc.service.UserService
import com.cognizant.cdc.util.UserSession
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping(value="user")
class UserController {
    
    final static int MAX_COOKIE_AGE = -1

    @Autowired
    UserService userService

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Map login(@RequestBody Map request, HttpServletResponse response) {

        String userName = request.userName
        String password = request.password

        if( userName == null || password == null) {
            return [
                    result: false,
                    error : "authetication error"
            ]
        }

        User user = userService.getUserByName(userName)

        if(user) {

            String token = userService.newSession(user.id)

            Cookie cookie = new Cookie("token", token)
            cookie.setMaxAge(MAX_COOKIE_AGE)
            cookie.setPath("/")

            response.addCookie(cookie)

            return [result : true]
        }

        [
            result: false,
            error : "user doesn't exist"
        ]
    }

    @RequestMapping(value="logout", method= RequestMethod.GET)
    @ResponseBody
    public Map logout() {
        User user = UserSession.getUser()
        if(user) {
            userService.logout(user.id)
        }

        [result: true]
    }

    @RequestMapping(value="info", method= RequestMethod.GET)
    @ResponseBody
    public Map info() {
        [result:  UserSession.getUser().toDBMap()]
    }
}
