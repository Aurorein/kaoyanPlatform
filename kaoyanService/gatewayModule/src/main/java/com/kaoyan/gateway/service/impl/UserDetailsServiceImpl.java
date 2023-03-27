package com.kaoyan.gateway.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;


import com.kaoyan.gateway.domain.LoginUser;
import com.kaoyan.gateway.entity.SysUser;
import com.kaoyan.gateway.mapper.SysPermissionMapper;
import com.kaoyan.gateway.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysPermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        TODO DELETE
        System.out.println("start loadUserByUserName function!");

        //查询用户信息
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserName,username);
        SysUser user = userMapper.selectOne(queryWrapper);
        //如果没有查询到用户就抛出异常
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或者密码错误");
        }
//        TODO DELETE
        System.out.println("成功从数据库中查询到user");
//        List<String> list = new ArrayList<>(Arrays.asList("test","admin"));
        List<String> list = permissionMapper.selectPermsByUserId(user.getId());
        //把数据封装成UserDetails返回
//        TODO DELETE
        System.out.println("----------");
        for(String permission:list){
            System.out.println("成功查到该用户权限:"+permission);
        }
        return new LoginUser(user,list);
    }
}
