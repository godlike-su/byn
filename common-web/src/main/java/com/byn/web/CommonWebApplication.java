package com.byn.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: `sujinwang`
 * @Date: `2022/4/3 23:43`
 * @Version: 1.0
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@MapperScan(value = {"com.byn.web.mapper"})
public class CommonWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonWebApplication.class, args);
    }
}
