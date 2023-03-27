package com.kaoyan.topicpost.service.impl;

import com.kaoyan.topicpost.entity.User;
import com.kaoyan.topicpost.mapper.UserMapper;
import com.kaoyan.topicpost.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cxn
 * @since 2023-03-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
