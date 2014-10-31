package com.cognizant.cdc.service

import org.junit.Test


class UserServiceTest {

    @Test
    void checkPassword() {

        UserService userService = new UserService()
        println userService.obfuscatePassword("123456", "Christine")

    }
}
