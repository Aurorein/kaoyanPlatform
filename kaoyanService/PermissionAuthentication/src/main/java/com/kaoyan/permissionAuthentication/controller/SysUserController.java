package com.kaoyan.permissionauthentication.controller;


import com.kaoyan.commonUtils.Res;
import com.kaoyan.permissionauthentication.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author cxn
 * @since 2023-03-04
 */
@RestController
@RequestMapping("/permissionauthentication/sys-user")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    @GetMapping("getAllUsers")
    public Res getAllUsers(){
        return Res.ok().data("users",sysUserService.list(null));
    }

}

