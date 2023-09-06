package com.kaoyan.permissionauthentication.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kaoyan.permissionauthentication.domain.RegisterParam;
import com.kaoyan.permissionauthentication.domain.UserRes;
import com.kaoyan.permissionauthentication.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cxn
 * @since 2023-06-30
 */
public interface UserService extends IService<User> {


    /**
     * @param user
     * @return
     */
    String login(User user);

    boolean register(RegisterParam registerParam);

    UserRes parseToUserRes(User user);


    Integer getFollowCount(Integer userId);

    Integer Create(Integer userId);

}
