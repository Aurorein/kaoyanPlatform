package com.kaoyan.topicpost.service;

import com.kaoyan.commonUtils.Res;
import com.kaoyan.topicpost.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cxn
 * @since 2023-03-27
 */
public interface UserService extends IService<User> {
    Res InsertAvatar(Integer userID,MultipartFile avatar,String username) throws FileNotFoundException;

    Res InsertUerRPC(Integer userID,String username);
}
