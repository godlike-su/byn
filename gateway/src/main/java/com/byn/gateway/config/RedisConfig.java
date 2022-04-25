package com.byn.gateway.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

/**
 * @Author: `sujinwang`
 * @Date: `2022/3/19 3:32`
 * @Version: 1.0
 * @Description:
 */
@Configuration //告诉Spring这是一个配置类
public class RedisConfig {
    /**
     * 下面自定义了一个redisTemplate
     * -->分别创建了两种序列化解析方式，来替换运来的JDK序列化解析方式
     * 方式一：Json序列化方式
     * 方式二：String类型的序列化方式
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory)
            throws UnknownHostException {
        //我们为了开发方便，直接使用<String, Object>类型
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        /**
         * 1. 方式一：创建Json的序列化方式
         *      -->配置Redis的序列化解析方式是Json
         *      -->即：通过Json去解析任何传进来的实体类对象，
         *      而不是使用原来的JDK去解析。
         */
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //1.1 Json的序列化方式，我们需要通过ObjectMapper进行转义
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.WRAPPER_ARRAY);
        //1.2 转义完就可以使用Json的序列化方式了
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return template;
    }
}