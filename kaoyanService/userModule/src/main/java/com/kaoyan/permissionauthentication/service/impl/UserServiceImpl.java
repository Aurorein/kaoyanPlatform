package com.kaoyan.permissionauthentication.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.kaoyan.commonUtils.JwtUtil;
import com.kaoyan.permissionauthentication.domain.RegisterParam;
import com.kaoyan.permissionauthentication.domain.UserRes;
import com.kaoyan.permissionauthentication.entity.User;
import com.kaoyan.permissionauthentication.feign.RemoteResourceService;
import com.kaoyan.permissionauthentication.mapper.UserMapper;
import com.kaoyan.permissionauthentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    private final static String PERMISSION_KEY = "permissions";

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RemoteResourceService remoteResourceService;

    @Override
    public String login(User user) {

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",user.getUsername());


        User userAuth = userMapper.selectOne(userQueryWrapper);
        boolean matches = passwordEncoder.matches(user.getPassword(), userAuth.getPassword());

        if(!matches){
            return "not_match";
        }
        String userId = String.valueOf(userAuth.getUserId());
        String userName = userAuth.getUsername();
        String signature = userAuth.getPersonalSignature();
        String email = userAuth.getEmail();
        String phone = userAuth.getPhone();

        // 根据用户信息生成jwt
        String jwt = JwtUtil.tokenProduces(userId, userName, signature, email, phone);
        System.out.println("jwt:"+jwt);

        // 查询当前用户可以访问的资源权限
        List<String> userResource = (List<String>)remoteResourceService.visible(Long.parseLong(userId)).getData().get("data");

        // 将用户权限放入缓存
        if(userResource != null && userResource.size() > 0){

            ValueOperations value = redisTemplate.opsForValue();
            value.set(PERMISSION_KEY +"-" +userId, userResource);

        }

//        HashMap<String,Object> map = new HashMap<>();
//        map.put("token",jwt);
//        map.put("loginuser",userAuth);
////        redisCache.setCacheObject("login:"+userAuth.getUserId(),map);
//        ValueOperations<String, Object> value = redisTemplate.opsForValue();
//        value.set("login:"+userAuth.getUserId(),map);
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
