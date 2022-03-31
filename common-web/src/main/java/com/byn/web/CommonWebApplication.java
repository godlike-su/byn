package com.byn.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: `sujinwang`
 * @Date: `2022/2/28 15:49`
 * @Version: 1.0
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.byn.web.mapper")
public class CommonWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonWebApplication.class, args);
    }
}
