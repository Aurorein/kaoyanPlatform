package com.kaoyan.gateway.filter;


import com.kaoyan.gateway.domain.LoginUser;
import com.kaoyan.gateway.utils.JwtUtil;
import com.kaoyan.gateway.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Objects;

@Component
public class JwtCustomGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    @Autowired
    private RedisCache redisCache;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            HttpHeaders headers = exchange.getRequest().getHeaders();
            String token = headers.getFirst("token");
            if (!StringUtils.hasText(token)) {
                return chain.filter(exchange);
            }
            //解析token
            String userid;
            try {
                Claims claims = JwtUtil.parseJWT(token);
                userid = claims.getSubject();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("token非法");
            }
            //从redis中获取用户信息
            String redisKey = "login:" + userid;
            HashMap<String,Object> userInfo = redisCache.getCacheObject(redisKey);

            if(Objects.isNull(userInfo.get("loginuser"))){
                throw new RuntimeException("用户未登录");
            }
            LoginUser loginUser = (LoginUser) userInfo.get("loginuser");
            // 存储用户信息到exchange
            exchange.getAttributes().put("user", loginUser);
            return chain.filter(exchange);
        };
    }
}