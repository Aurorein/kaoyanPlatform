package com.kaoyan.kaoyanforum.feign;

import com.kaoyan.commonUtils.Res;
import com.kaoyan.kaoyanforum.domain.UserRes;
import com.kaoyan.kaoyanforum.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "authen-service",path = "/api/user")
public interface RemoteUserService {


    @GetMapping("set")
    public Res setUser(User user);

    @GetMapping("info/{userId}")
    public Res getUserInfo(@PathVariable Integer userId);

    @GetMapping("parse")
    public UserRes parseToUserRes(User user);

    @GetMapping("follow-count")
    Integer getFollowCount(Integer userId);

    @GetMapping("create")
    Integer Create(Integer userId);
}
