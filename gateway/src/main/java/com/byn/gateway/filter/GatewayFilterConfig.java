package com.byn.gateway.filter;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.byn.gateway.entity.User;
import com.byn.gateway.util.JwtVerity;
import com.byn.gateway.util.RedisUtil;
import com.github.pagehelper.util.StringUtil;
import com.result.ErrorResult;
import com.result.ReturnStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Consumer;

/**
 * 自定义路由过滤器
 * 路由过滤器可用于修改进入的http请求和返回的http相应，路由过滤器只能指定路由进行使用
 */
@Component
@Slf4j
public class GatewayFilterConfig implements GlobalFilter, Ordered {

    @Value("${gateway.white.url}")
    private String[] whiteUrl;

    /**
     * 请求头
     */
    @Value("${jwt.token.header}")
    private String header;

    @Value("${jwt.token.key}")
    private String tokenKey;

    /**
     * 请求头 用户id
     */
    @Value("${jwt.userId}")
    private String userId;

    /**
     * 请求头 用户名
     */
    @Value("${jwt.userName}")
    private String userName;
    /**
     * 请求头 session
     */
    @Value("${jwt.session}")
    private String session;

    private static final String BYN_AUTHORIZATION = "byn_authorization";
    private static final long TIMEOUT_SECOND = 86400;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestUrl = exchange.getRequest().getPath().value();
        AntPathMatcher pathMatcher = new AntPathMatcher();
        // 白名单放行
        if (!ObjectUtils.isEmpty(whiteUrl)) {
            for (String url : whiteUrl) {
                // 1.对服务进行放行  pathMatcher.match("/uaa/**",requestUrl)路径是否匹配
                if (pathMatcher.match(url, requestUrl)) {
                    return chain.filter(exchange);
                }
            }
        }
        // 获取请求头
        List<String> authorization = exchange.getRequest().getHeaders().get(header);
        // 从token中获取session,并提取出数据
        String token = getToken(authorization);
        User user = getUser(token);
        if (ObjectUtils.isEmpty(user)) {
            return out(exchange.getResponse(), ReturnStatus.STATUS_UNAUTHORIZED, "请登录后重试!");
        }
        // 将username，加入头请求
        Consumer<HttpHeaders> httpHeaders = httpHeader -> {
            httpHeader.set(userName, user.getUserName());
            httpHeader.set(userId, user.getUserId());
            httpHeader.set(session, token);
        };
        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers(httpHeaders).build();
        exchange.mutate().request(serverHttpRequest).build();
        return chain.filter(exchange);
    }

    private String getToken(List<String> authorization) {
        if (ObjectUtils.isEmpty(authorization)) {
            return null;
        }
        // 获取token
        String token = authorization.get(0);
        if (StringUtil.isEmpty(token)) {
            return null;
        }
        // 从redis中取出token
        RedisUtil redisUtil = new RedisUtil();
        Object hget = redisUtil.hget(BYN_AUTHORIZATION, token);
        if (ObjectUtils.isEmpty(hget)) {
            return null;
        }
        // 重新设置超时时间
        redisUtil.hset(BYN_AUTHORIZATION, token, hget, TIMEOUT_SECOND);
        return hget.toString();
    }

    /**
     * 判断jwt是否真实，超时等
     *
     * @param jwtToken
     * @return
     */
    private User getUser(String jwtToken) {
        // 判断路径是否需要用户权限，暂时未做，也可以判断redis是否存有该key
        // 解析token，判断是否超时了
        try {
            JwtVerity jwtVerity = new JwtVerity();
            boolean verity = jwtVerity.verity(tokenKey, jwtToken);
            if (!verity) {
                return null;
            }
            JWT jwt = JWTUtil.parseToken(jwtToken);
            JWTPayload payload = jwt.getPayload();
            String id = (String) payload.getClaim(userId);
            String name = (String) payload.getClaim(userName);
            // 根据取出userId，去数据库或redis查询，是否有该数据
            User user = new User();
            user.setUserId(id);
            user.setUserName(name);
            if (ObjectUtils.isEmpty(user)) {
                return null;
            }
            return user;
        } catch (Exception e) {
            log.error("jwt解析错误: ", e);
            return null;
        }
    }


    /**
     * 未登录自定义返回
     *
     * @param response
     * @return
     */
    private Mono<Void> out(ServerHttpResponse response, String status, String message) {
        response.getHeaders().add("Content-Type", "application/json;charsets=UTF-8");
        DataBufferFactory dataBufferFactory = response.bufferFactory();
        DataBuffer dataBuffer
                = dataBufferFactory.wrap(new ErrorResult(status, message)
                .toString().getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(dataBuffer));
    }


    @Override
    public int getOrder() {
        return -10;
    }
}
