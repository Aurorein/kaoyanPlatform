package com.kaoyan.permissionauthentication.controller;



import com.kaoyan.commonUtils.Res;

import com.kaoyan.permissionauthentication.domain.RegisterParam;
import com.kaoyan.permissionauthentication.domain.UserRes;
import com.kaoyan.permissionauthentication.entity.User;
import com.kaoyan.permissionauthentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cxn
 * @since 2023-06-30
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/system/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("login")
    public Res login(@RequestBody Map<String, Object> jsonMap){
        String username = (String)jsonMap.get("username");
        String password = (String)jsonMap.get("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        String token = userService.login(user);
        if("not_match".equals(token)){
            return Res.error().message("用户名或密码错误");
        }

        return Res.ok().data("token",token);
    }

    @PostMapping("register")
    public Res register(@RequestBody RegisterParam registerParam){

        boolean isSave = userService.register(registerParam);
        if(isSave){
            return Res.ok().message("注册成功");
        }else{
            return Res.error().message("注册失败");
        }

    }





    @GetMapping("info/{userId}")
    public Res getUserInfo(@PathVariable Integer userId){
        return Res.ok().data("user",userService.getById(userId));
    }

    @GetMapping("parse")
    public UserRes parseToUserRes(User user){return userService.parseToUserRes(user);}

    @GetMapping("follow-count")
    Integer getFollowCount(Integer userId){return userService.getFollowCount(userId);}

    @GetMapping("create")
    Integer Create(Integer userId){return userService.Create(userId);}
}

