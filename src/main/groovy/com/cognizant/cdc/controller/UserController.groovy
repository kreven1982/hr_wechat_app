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
import com.cognizant.cdc.model.enums.LoginStatus
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PathVariable

@Controller
@RequestMapping(value="user")
class UserController {
    
    final static int MAX_COOKIE_AGE = -1

    @Autowired
    UserService userService

    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ResponseBody
    public Map login(@RequestParam String userName, @RequestParam String password, HttpServletResponse response) {

        if(LoginStatus.Success != userService.checkCredential(userName, password)) {
            return [
                    result: false,
                    error : "authetication error"
            ]
        }

        String token = userService.newSession(userName)

        Cookie cookie = new Cookie("token", token)
        cookie.setMaxAge(MAX_COOKIE_AGE)
        cookie.setPath("/")

        response.addCookie(cookie)

        return [result : true]
    }

    @RequestMapping(value="logout", method= RequestMethod.GET)
    @ResponseBody
    public Map logout() {
        User user = UserSession.getUser()
        if(user) {
            userService.logout(user.userName)
        }

        [result: true]
    }

    @RequestMapping(value="info", method= RequestMethod.GET)
    @ResponseBody
    public Map info(@RequestParam(value="userId", required = false) Long userId) {
        User user = null

        if(userId) {
            user = userService.getUserById(userId)
        } else {
            user = UserSession.getUser()
        }

        [result:  user?.toRepresentationMap()]
    }

    @RequestMapping(value="list", method= RequestMethod.GET)
    @ResponseBody
    public Map list() {
        List<User> users = userService.getUsers()
        [users : users*.toRepresentationMap()]
    }

    @RequestMapping(value="{id}", method= RequestMethod.POST)
    @ResponseBody
    public Map newUser(@PathVariable("id") Integer userId, @RequestBody User user) {

        if(!userId) {
            userService.newUser(user)
        } else {

            if(userId != user.id) {
                throw new RuntimeException("Should not happen, might be security threat!")
            }

            userService.updateUser(user)
        }

       [success : true ]
    }
}
