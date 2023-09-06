package com.kaoyan.permissionauthentication.utils;

import com.kaoyan.permissionauthentication.entity.User;

public class SecurityUtil {

    private static User user;

    public static User getUser(){
        return user;
    }

    public static void setUser(User tUser){
        user = tUser;
    }
}
