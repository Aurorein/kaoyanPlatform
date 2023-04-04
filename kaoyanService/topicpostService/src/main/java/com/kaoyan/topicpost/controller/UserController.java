package com.kaoyan.topicpost.controller;


import com.kaoyan.commonUtils.Res;
import com.kaoyan.topicpost.entity.User;
import com.kaoyan.topicpost.service.UserService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cxn
 * @since 2023-03-27
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/community/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/insert-test")
    public Res InsertUserTest(@RequestParam("userID") Integer userID,@RequestParam("username") String username) throws FileNotFoundException {
//        Integer userID  = (Integer) list.get(0);
//        MultipartFile avatar = (MultipartFile) list.get(1);
//        String username = (String) list.get(2);
        return userService.InsertUerRPC(userID,username);
    }

    @PostMapping("/insert-avatar")
    public Res InsertUser(@RequestParam Integer userID,@RequestParam MultipartFile avatar,@RequestParam String username) throws FileNotFoundException {

        return userService.InsertAvatar(userID,avatar,username);
    }
}

