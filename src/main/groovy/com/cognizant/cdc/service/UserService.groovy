package com.cognizant.cdc.service

import com.cognizant.cdc.model.User
import com.cognizant.cdc.repository.SequenceRepository
import com.cognizant.cdc.repository.UserRepository
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.bson.types.ObjectId

@CompileStatic
@TypeChecked
@Service
class UserService {

    @Autowired
    UserRepository userRepository

    @Autowired
    SequenceRepository  sequenceRepository

//    //Create an user
    public void newUser(User user) {
        userRepository.newUser(user)
    }
//
//    //Remove an user
//    public void deleteUser(long userId) {
//
//    }

    public User getUserByName(String userName) {
        userRepository.getUserByName(userName)
    }

    //Retrieve user by token
    public User getUserByToken(String token) {
        userRepository.getUserByToken(token)
    }

    //Login and associate user with a token
    public String newSession(long userId) {
        String token = new ObjectId().toString()
        userRepository.updateToken(userId, token)
        token
    }

    //Logout session
    public void logout(long userId) {
        userRepository.updateToken(userId, null)
    }
}
