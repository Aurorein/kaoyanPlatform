package com.kaoyan.permissionauthentication.feign;

import com.kaoyan.commonUtils.Res;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "authen-service",path = "/api/system/resource")
public interface RemoteResourceService {

    @GetMapping
    Res list();

    @GetMapping("/resource/{userId}")
    Res visible(@PathVariable Long userId);
}
