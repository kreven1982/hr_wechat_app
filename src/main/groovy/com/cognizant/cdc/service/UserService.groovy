package com.cognizant.cdc.service

import com.cognizant.cdc.model.User
import com.cognizant.cdc.repository.SequenceRepository
import com.cognizant.cdc.repository.UserRepository
import groovy.transform.CompileStatic
import groovy.transform.TypeChecked
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.bson.types.ObjectId
import com.cognizant.cdc.model.enums.LoginStatus
import com.cognizant.cdc.util.Utils

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

        user.id = sequenceRepository.getNextID("USER_SEQ")
        user.createTime = System.currentTimeMillis()
        //Initial password
        user.password = obfuscatePassword(user.password, user.userName)
        userRepository.newUser(user)
    }

    public void updateUser(User user) {
        user.password = obfuscatePassword(user.password, user.userName)
        userRepository.updateUser(user)
    }

    //Remove an user
    public void deleteUser(long userId) {
        userRepository.removeUser(userId)
    }

    public LoginStatus checkCredential(String userName, String password) {
        User user = userRepository.getUserByName(userName)

        if(!user) {
            return LoginStatus.UserNotExist
        }

        if(!obfuscatePassword(password, userName).equals(user.password)) {
            return LoginStatus.WrongPassword
        }

        LoginStatus.Success
    }

    //Retrieve user by token
    public User getUserByToken(String token) {
        userRepository.getUserByToken(token)
    }


    public User getUserById(long userId) {
        userRepository.getUserById(userId)
    }


    public List<User> getUsers() {
        userRepository.getUsers()
    }

    //Login and associate user with a token
    public String newSession(String userName) {
        String token = new ObjectId().toString()
        userRepository.updateToken(userName, token)
        token
    }

    //Logout session
    public void logout(String userName) {
        userRepository.updateToken(userName, null)
    }

    private static String PASS_KEY = "COGNIZANT_HR_APP"
    protected String obfuscatePassword(String password, String username) {
        String obfuscatedString = PASS_KEY + password + username
        return Utils.sha1(obfuscatedString)
    }

}
