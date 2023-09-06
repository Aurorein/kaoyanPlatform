package com.kaoyan.permissionauthentication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.kaoyan.commonUtils.JwtUtil;
import com.kaoyan.permissionauthentication.domain.RegisterParam;
import com.kaoyan.permissionauthentication.domain.UserRes;
import com.kaoyan.permissionauthentication.entity.User;
import com.kaoyan.permissionauthentication.mapper.UserMapper;
import com.kaoyan.permissionauthentication.service.UserService;
import com.kaoyan.permissionauthentication.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cxn
 * @since 2023-06-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public String login(User user) {

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",user.getUsername());


        User userAuth = userMapper.selectOne(userQueryWrapper);
        boolean matches = passwordEncoder.matches(user.getPassword(), userAuth.getPassword());

        if(!matches){
            return "not_match";
        }
        SecurityUtil.setUser(userAuth);
        String userId = String.valueOf(userAuth.getUserId());


        String jwt = JwtUtil.tokenProduces(userId);

        System.out.println("jwt:"+jwt);

        HashMap<String,Object> map = new HashMap<>();
        map.put("token",jwt);
        map.put("loginuser",userAuth);
//        redisCache.setCacheObject("login:"+userAuth.getUserId(),map);
        ValueOperations<String, Object> value = redisTemplate.opsForValue();
        value.set("login:"+userAuth.getUserId(),map);
        return jwt;

    }

    @Override
    public boolean register(RegisterParam registerParam) {
        String passwordProcessed = passwordEncoder.encode(registerParam.getPassword());
        User user = new User();
        user.setUsername(registerParam.getUsername());
        user.setPassword(passwordProcessed);
        user.setPersonalSignature(registerParam.getPersonalSignature());
        user.setEmail(registerParam.getEmail());
        user.setPhone(registerParam.getPhone());

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",user.getUsername());
        if(userMapper.selectOne(userQueryWrapper) != null){
            return false;
        }
        int insert = userMapper.insert(user);
        if(insert == 0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public UserRes parseToUserRes(User user) {
        UserRes userRes = new UserRes();
        userRes.setUserId(user.getUserId());
        userRes.setUserNickname(user.getUsername());
        userRes.setUserSignature(user.getPersonalSignature());
        return userRes;
    }

    @Override
    public Integer getFollowCount(Integer userId) {
        return userMapper.getFollowCount(userId);
    }

    @Override
    public Integer Create(Integer userId) {
        return userMapper.getCreate(userId);
    }


}
