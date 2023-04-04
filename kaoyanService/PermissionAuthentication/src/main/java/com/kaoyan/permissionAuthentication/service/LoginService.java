package com.kaoyan.permissionauthentication.service;

import com.kaoyan.commonUtils.Res;
import com.kaoyan.permissionauthentication.entity.SysUser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


public interface LoginService {
    Res login(SysUser user);

    Res logout();

    Res signup(String username, String password, int role);
}
