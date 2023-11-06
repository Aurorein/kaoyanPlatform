package com.kaoyan.gateway.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.kaoyan.commonUtils.JwtUtil;
import com.kaoyan.commonUtils.StringUtils;

import com.kaoyan.commonUtils.WebUtils;
import com.kaoyan.commonUtils.constant.CacheConstants;
import com.kaoyan.commonUtils.constant.TokenConstants;

import com.kaoyan.gateway.config.IgnoreWhiteProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;


/**
 * 网关鉴权
 * 
 * @author ruoyi
 */
@Slf4j
@Component
@AllArgsConstructor
public class AuthFilter implements GlobalFilter, Ordered
{

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

        log.info("[网关认证服务]请求url:{}",url);
        // 跳过不需要验证的路径
        if (StringUtils.matches(url, ignoreWhite.getWhites()))
        {
            return chain.filter(exchange);
        }


        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst("token").trim();

        try {
            JwtUtil.verify(token);
            DecodedJWT tokenInfo = JwtUtil.getTokenInfo(token);

            addHeader(mutate, "userId", tokenInfo.getClaim("userId").asString());
            addHeader(mutate, "userName", tokenInfo.getClaim("userName").asString());
            addHeader(mutate, "signature", tokenInfo.getClaim("signature").asString());
            addHeader(mutate, "email", tokenInfo.getClaim("email").asString());
            addHeader(mutate, "phone", tokenInfo.getClaim("phone").asString());

        } catch (Exception e) {
            e.printStackTrace();
            return errorResponse(exchange, "token认证失败！");
        }
        //从redis中获取用户信息(不使用，而是改成redis存储权限信息，用户信息放在token里面）
//        String redisKey = "login:" + userId;
//        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(redisKey);
//
//        if(Objects.isNull(boundValueOperations)){
//            throw new RuntimeException("用户未登录");
//        }

//        User loginUser = (User)boundValueOperations.get();
//        HashMap<String,Object> userInfo = redisCache.getCacheObject(redisKey);

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

    private Mono<Void> errorResponse(ServerWebExchange exchange, String message) {
        ServerHttpResponse response = exchange.getResponse();
        byte[] bits = message.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        log.error("[鉴权异常处理]请求路径:{}", exchange.getRequest());
        return response.writeWith(Mono.just(buffer));
    }
}