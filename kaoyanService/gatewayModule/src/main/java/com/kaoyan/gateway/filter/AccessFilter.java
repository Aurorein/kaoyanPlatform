package com.kaoyan.gateway.filter;

import com.kaoyan.commonUtils.Res;
import com.kaoyan.gateway.feign.RemoteResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class AccessFilter implements GlobalFilter, Ordered {

    private final static String RESOURCES_KEY = "resources";
    private final static String PERMISSION_KEY = "permissions";

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RemoteResourceService remoteResourceService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        String method = request.getMethod().name();
        String url = request.getURI().toString();

        String substring = url.substring(4);
        // 通过url和方法拼接出来权限标识符
        String permission = method + substring;

        // 从缓存中获取所有需要进行鉴权的资源，如果没有则通过Feign调用权限服务获取并放入缓存
        BoundValueOperations resources = redisTemplate.boundValueOps(RESOURCES_KEY);
        List<String> ps;
        if(resources != null){
            ps = (List<String>)resources.get();
        }else{
            // 调用权限远程服务获得资源
            Res res = remoteResourceService.list();
            ps = (List<String>)res.getData().get("data");
            // 放入缓存中
            if(ps != null && ps.size() > 0){
                ValueOperations value = redisTemplate.opsForValue();
                value.set(RESOURCES_KEY, ps);
            }
        }

        // 判断这些资源是都包含当前请求的权限标识符，如果不包含，则返回未经授权的错误
        long count = ps.stream().filter((r) -> {
            return permission.startsWith(r);
        }).count();

        if(count == 0){
            errorResponse(exchange, "未授权！");
            return null;
        }

        // 如果包含当前的权限标识符，则从header中取出id，根据用户id取出缓存中用户拥有的权限，如果没有取到则通过feign调用权限服务获取并放入缓存中，判断用户的权限是否包含当前请求的权限标识符
        String id = exchange.getRequest().getHeaders().getFirst("id");

        // 尝试从redis中获取
        BoundValueOperations userPs = redisTemplate.boundValueOps(PERMISSION_KEY + "-" + id);
        List<String> visibleResources;
        if(userPs == null){
            // 通过接口远程调用权限服务来获取
            visibleResources = (List<String>)remoteResourceService.visible(Long.parseLong(id)).getData().get("data");

            // 放入缓存中
            ValueOperations ops = redisTemplate.opsForValue();
            ops.set(PERMISSION_KEY + "-" + id, visibleResources);
        }else{
            visibleResources = (List<String>) userPs.get();
        }

        // 如果用户拥有的权限包含当前请求的权限标识符则说明当前用户拥有权限，直接放行
        count = visibleResources.stream().filter((r) -> {
            return permission.startsWith(r);
        }).count();

        if(count > 0){
            // 当前用户拥有该权限，直接放行
            return chain.filter(exchange);
        }else{
            errorResponse(exchange, "无权限访问该资源！");
            return null;
        }
    }

    @Override
    public int getOrder() {
        return 10;
    }

    private Mono<Void> errorResponse(ServerWebExchange exchange, String message) {
        ServerHttpResponse response = exchange.getResponse();
        byte[] bits = message.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        log.error("{}", message);
        return response.writeWith(Mono.just(buffer));
    }
}
