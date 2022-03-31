package com.byn.gateway.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * jwt验证
 *
 * @author `sujinwang`
 * @version v1.0.0.0
 * @Date `2022/2/24 11:00`
 */
public class JwtVerity {

    public boolean verity(String key, String token) {
        try {
            // 验证jwt是否合法
            JWT jwt = JWTUtil.parseToken(token);
            boolean verify = jwt.setKey(key.getBytes()).verify();
            if (!verify) {
                return false;
            }
            // 验证是否失效
            JWTPayload payload = jwt.getPayload();
            String exp = payload.getClaim("exp").toString();
            if (Long.parseLong(exp) < DateTime.now().getTime()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String encode(String rawPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        // security登录加密
        String encode = bCryptPasswordEncoder.encode(rawPassword);

        return encode;
    }

    public static void main(String[] args) {
        List list = new ArrayList();
    }
}
