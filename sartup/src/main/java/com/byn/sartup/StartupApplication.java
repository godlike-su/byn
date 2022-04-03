package com.byn.sartup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
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
public class StartupApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartupApplication.class, args);
    }
}
