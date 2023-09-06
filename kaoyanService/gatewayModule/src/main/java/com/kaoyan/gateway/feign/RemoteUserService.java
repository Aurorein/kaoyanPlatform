package com.kaoyan.gateway.feign;

import com.kaoyan.commonUtils.Res;

import com.kaoyan.gateway.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "authen-service",path = "/api/user")
public interface RemoteUserService {


    @GetMapping("set")
    public Res setUser(User user);
}
