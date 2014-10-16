package com.cognizant.cdc.util

import com.cognizant.cdc.model.User


class UserSession {

    final private static ThreadLocal<User> userThreadLocal = new ThreadLocal<User>()

    public static void setUser(User user) {
        userThreadLocal.set(user)
    }

    public static User getUser() {
        userThreadLocal.get()
    }
}
