package com.byn.client;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: `sujinwang`
 * @Date: `2022/2/27 2:57`
 * @Version: 1.0
 * @Description: 前台页面主要显示接口
 */
@Configuration
@ComponentScan(value = {"com.byn.client"})
@MapperScan(value = {"com.byn.client.mapper"})
public class ClienetConfiguration {
    public static void main(String[] args) {
        SpringApplication.run(ClienetConfiguration.class, args);
    }
}