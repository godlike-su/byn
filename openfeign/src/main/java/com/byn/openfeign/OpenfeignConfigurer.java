package com.byn.openfeign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: `sujinwang`
 * @Date: `2022/2/28 10:49`
 * @Version: 1.0
 * @Description:
 */
@Configuration
@EnableFeignClients
@ComponentScan(basePackages = {"com.byn.openfeign"})
public class OpenfeignConfigurer {
}
