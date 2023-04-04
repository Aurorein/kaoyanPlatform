package com.kaoyan.topicpost.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kaoyan.commonUtils.Res;
import com.kaoyan.topicpost.entity.User;
import com.kaoyan.topicpost.mapper.UserMapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaoyan.topicpost.service.UserService;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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

    @Override
    public Res InsertAvatar(Integer userID,MultipartFile avatar,String username) throws FileNotFoundException {
        File folder=new File(ResourceUtils.getURL("classpath:").getPath()+"static/avatars");
        if(!folder.isDirectory()){
            folder.mkdirs();
        }
        String fileName = avatar.getOriginalFilename();
        String destFileName = username+fileName.substring(fileName.lastIndexOf("."),fileName.length());
        try {
            avatar.transferTo(new File(folder, destFileName));
            System.out.println(new File(folder, destFileName).getAbsolutePath());//输出（上传文件）保存的绝对路径
//            String filePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/"+destFileName;
            User user = new User(userID,username,destFileName,1,new java.sql.Timestamp(System.currentTimeMillis()),new java.sql.Timestamp(System.currentTimeMillis()));
            updateById(user);
            return Res.ok().data("data","插入成功！");
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return Res.error().data("data","插入失败！");
    }

    @Override
    public Res InsertUerRPC(Integer userID, String username) {
        User user = new User(userID,username,null,1,new java.sql.Timestamp(System.currentTimeMillis()),new java.sql.Timestamp(System.currentTimeMillis()));
        save(user);
        return Res.ok().data("data","插入成功！");
    }
}
