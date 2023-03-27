package com.kaoyan.gateway.service;

import com.kaoyan.commonUtils.Res;
import com.kaoyan.gateway.entity.SysUser;
import org.springframework.stereotype.Service;


public interface LoginService {
    Res login(SysUser user);

    Res logout();

    Res signup(String username,String password,int role);
}
