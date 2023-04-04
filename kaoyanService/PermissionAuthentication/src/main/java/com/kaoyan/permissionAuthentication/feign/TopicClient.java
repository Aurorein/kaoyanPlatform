package com.kaoyan.permissionauthentication.feign;

import com.kaoyan.commonUtils.Res;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@FeignClient(value = "topicpost-service",path = "/api/community/user")
public interface TopicClient {


    @GetMapping("/insert-test")
    public Res InsertUserTest(@RequestParam("userID") Integer userID,@RequestParam("username") String username);
}
