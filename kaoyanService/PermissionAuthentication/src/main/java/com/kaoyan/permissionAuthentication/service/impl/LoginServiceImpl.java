package com.kaoyan.permissionauthentication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kaoyan.commonUtils.Res;
import com.kaoyan.permissionauthentication.domain.LoginUser;
import com.kaoyan.permissionauthentication.entity.SysUser;
import com.kaoyan.permissionauthentication.entity.SysUserRoleRelation;
import com.kaoyan.permissionauthentication.service.LoginService;
import com.kaoyan.permissionauthentication.service.SysUserRoleRelationService;
import com.kaoyan.permissionauthentication.service.SysUserService;
import com.kaoyan.permissionauthentication.utils.JwtUtil;
import com.kaoyan.permissionauthentication.utils.RedisCache;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RedisCache redisCache;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysUserRoleRelationService sysUserRoleRelationService;

    @Override
    public Res login(SysUser user) {
        // AuthenticationManager authenticate进行用户认证
        // TODO delete
        System.out.println("username:"+user.getUserName()+"password:"+user.getPassword());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("登陆失败！");
        }

        //TODO delete
        System.out.println("authenticate:"+authenticate.toString());
        // 认证通过，用userId生成JWT，存入redis



        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        // TODO delete
        System.out.println("loginUserID:"+loginUser.getUser().getId().toString());
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

       // TODO delete
        System.out.println("jwt:"+jwt);

        HashMap<String,Object> map = new HashMap<>();
        map.put("token",jwt);
        map.put("loginuser",loginUser);
        redisCache.setCacheObject("login:"+loginUser.getUser().getId(),map);
        return Res.ok().data("token",jwt);

    }

    @Override
    public Res logout() {
        // 获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser)authenticationToken.getPrincipal();
        // 删除redis中的值
        Integer id = loginUser.getUser().getId();
        redisCache.deleteObject("login:"+id);
        return Res.ok().data("msg","注销成功！");
    }

    /**
     *
     * @param username 用户名
     * @param password 密码
     * @param role 角色（前台系统登录则角色为
     * @return
     */
    @Override
    public Res signup(String username,String password,int role) {
        if(role > 3 || role < 1){
            return Res.error().data("msg","role值错误！");
        }
        String passwordProcessed = passwordEncoder.encode(password);
        SysUser user = new SysUser();
        user.setUserName(username);
        user.setPassword(passwordProcessed);
        user.setEnabled(true);
        sysUserService.save(user);
        SysUserRoleRelation sysUserRoleRelation = new SysUserRoleRelation();
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>().eq("user_name", user.getUserName());
        sysUserRoleRelation.setUserId(sysUserService.getOne(queryWrapper).getId());
        sysUserRoleRelation.setRoleId(role);
        boolean isSave = sysUserRoleRelationService.save(sysUserRoleRelation);
        if(isSave){
            return Res.ok().data("msg",username+"注册成功！");
        }else{
            return Res.ok().data("msg","注册失败！");
        }
    }
}
