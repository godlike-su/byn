package com.byn.gateway.filter;

import com.byn.gateway.dao.UserDao;
import com.byn.gateway.entity.Role;
import com.result.ErrorResult;
import com.result.ReturnStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 自定义路由过滤器
 * 路由过滤器可用于修改进入的http请求和返回的http相应，路由过滤器只能指定路由进行使用
 */
@Component
@Slf4j
public class AuthorizeVipFilterConfig implements GlobalFilter, Ordered {
    @Autowired
    private UserDao userDao;

    /**
     * 需要vip鉴权的路径
     */
    @Value("${gateway.authorize.type.vip}")
    private String[] vipUrl;

    /**
     * 请求头 用户id
     */
    @Value("${jwt.userId}")
    private String userId;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestUrl = exchange.getRequest().getPath().value();
        AntPathMatcher pathMatcher = new AntPathMatcher();
        // 判断是否需要授权
        boolean isAuthor = false;
        // 需要vip权限的路径判断
        if (!ObjectUtils.isEmpty(vipUrl)) {
            for (String url : vipUrl) {
                // 1.对服务进行放行  pathMatcher.match("/uaa/**",requestUrl)路径是否匹配
                if (pathMatcher.match(url, requestUrl)) {
                    isAuthor = true;
                    break;
                }
            }
        }
        // 无需授权直接返回
        if (!isAuthor) {
            return chain.filter(exchange);
        }

        // 获取请求头
        List<String> id = exchange.getRequest().getHeaders().get(userId);
        if (ObjectUtils.isEmpty(id)) {
            log.error("vip鉴权报错,获取不到userId,请检查是否在白名单中添加了此路径");
            return out(exchange.getResponse(), ReturnStatus.STATUS_FORBIDDEN, "获取不到id!");
        }
        // 判断权限
        if (authorizationHandler(id.get(0), "vip")) {
            return out(exchange.getResponse(), ReturnStatus.STATUS_FORBIDDEN, "权限不足!");
        }

        return chain.filter(exchange);
    }

    /**
     * 判断权限
     *
     * @param uId
     * @param rName
     * @return
     */
    private boolean authorizationHandler(String uId, String rName) {
        // 如果不存在vip权限，则返回true，鉴权不通过
        List<Role> roles = userDao.getRolesByUidAndRname(uId, rName);
        if (ObjectUtils.isEmpty(roles)) {
            return true;
        } else {
            return false;
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
        return -9;
    }
}
