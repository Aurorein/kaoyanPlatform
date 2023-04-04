package com.kaoyan.feignapi.clients;

import com.kaoyan.commonUtils.Res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.List;

@FeignClient(value = "topicpost-service",path = "/api/community/user")
public interface UserClient {


    @RequestMapping("/insert")
    public Res InsertUser(List list) ;
}
