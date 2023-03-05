package com.kaoyan.permissionauthentication.controller;


import com.kaoyan.commonUtils.Res;
import com.kaoyan.permissionauthentication.entity.SysUser;
import com.kaoyan.permissionauthentication.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permission/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("user/login")
    public Res login(@RequestParam String username,
                     @RequestParam String password){
        SysUser user = new SysUser();
        user.setUserName(username);
        user.setPassword(password);
        return loginService.login(user);
    }

    @GetMapping("user/logout")
    public Res logout(){
        return loginService.logout();
    }

    @PostMapping("user/signup")
    public Res signup(@RequestParam String username,
                      @RequestParam String password,
                      @RequestParam int role){
        return loginService.signup(username,password,role);
    }

}
