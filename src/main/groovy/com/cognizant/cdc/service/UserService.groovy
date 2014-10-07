package com.cognizant.cdc.service

import com.cognizant.cdc.model.User
import com.cognizant.cdc.repository.SequenceRepository
import com.cognizant.cdc.repository.UserRepository
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@CompileStatic
@TypeChecked
@Service
class UserService {

    @Autowired
    UserRepository userRepository

    @Autowired
    SequenceRepository  sequenceRepository

    //Create an user
    public void newUser(User user) {
        null
    }

    //Remove an user
    public void deleteUser(long userId) {

    }

    //Retrieve user by token
    public User getUserByToken(String Token) {
        null
    }

    //Login and associate user with a token
    public String login(long userId) {
       null
    }

    //Logout session
    public void logout(long userId) {

    }
}
