package com.kaoyan.gateway.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.kaoyan.commonUtils.JwtUtil;
import com.kaoyan.commonUtils.StringUtils;

import com.kaoyan.commonUtils.WebUtils;
import com.kaoyan.commonUtils.constant.CacheConstants;
import com.kaoyan.commonUtils.constant.SecurityConstants;
import com.kaoyan.commonUtils.constant.TokenConstants;

import com.kaoyan.gateway.config.IgnoreWhiteProperties;
import com.kaoyan.gateway.entity.User;
import com.kaoyan.gateway.feign.RemoteUserService;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;


/**
 * 网关鉴权
 * 
 * @author ruoyi
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered
{
    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);

    // 排除过滤的 uri 地址，nacos自行添加
    @Autowired
    private IgnoreWhiteProperties ignoreWhite;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    RemoteUserService remoteUserService;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();

        String url = request.getURI().getPath();
        // 跳过不需要验证的路径
        if (StringUtils.matches(url, ignoreWhite.getWhites()))
        {
            return chain.filter(exchange);
        }
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst("token");


        String userId;
        try {
            JwtUtil.verify(token);
            DecodedJWT tokenInfo = JwtUtil.getTokenInfo(token);
            userId = tokenInfo.getClaim("userId").asString();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token非法");
        }
        //从redis中获取用户信息
        String redisKey = "login:" + userId;
        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(redisKey);

        if(Objects.isNull(boundValueOperations)){
            throw new RuntimeException("用户未登录");
        }

        User loginUser = (User)boundValueOperations.get();
//        HashMap<String,Object> userInfo = redisCache.getCacheObject(redisKey);



//        User loginuser = (User) userInfo.get("loginuser");
        remoteUserService.setUser(loginUser);
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }

    private void addHeader(ServerHttpRequest.Builder mutate, String name, Object value)
    {
        if (value == null)
        {
            return;
        }
        String valueStr = value.toString();
        String valueEncode = WebUtils.urlEncode(valueStr);
        mutate.header(name, valueEncode);
    }

    private void removeHeader(ServerHttpRequest.Builder mutate, String name)
    {
        mutate.headers(httpHeaders -> httpHeaders.remove(name)).build();
    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, String msg)
    {
        log.error("[鉴权异常处理]请求路径:{}", exchange.getRequest().getPath());
                return WebUtils.webFluxResponseWriter(exchange.getResponse(), msg, 401);
    }

    /**
     * 获取缓存key
     */
    private String getTokenKey(String token)
    {
        return CacheConstants.LOGIN_TOKEN_KEY + token;
    }

    /**
     * 获取请求token
     */
    private String getToken(ServerHttpRequest request)
    {
        String token = request.getHeaders().getFirst(TokenConstants.AUTHENTICATION);
        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (!StringUtils.isEmpty(token) && token.startsWith(TokenConstants.PREFIX))
        {
            token = token.replaceFirst(TokenConstants.PREFIX, StringUtils.EMPTY);
        }
        return token;
    }

    @Override
    public int getOrder()
    {
        return -200;
    }
}