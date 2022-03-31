package com.byn.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: `sujinwang`
 * @Date: `2022/2/27 2:57`
 * @Version: 1.0
 * @Description: 前台页面主要显示接口
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@EnableFeignClients
public class ClienetApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClienetApplication.class, args);
    }
}
