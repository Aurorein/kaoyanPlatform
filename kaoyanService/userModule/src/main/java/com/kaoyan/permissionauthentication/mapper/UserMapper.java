package com.kaoyan.permissionauthentication.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.kaoyan.permissionauthentication.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cxn
 * @since 2023-06-30
 */
@Repository
public interface UserMapper extends BaseMapper<User> {


    Integer getFollowCount(@Param("userId") Integer userId);

    Integer getCreate(@Param("userId") Integer userId);


}
