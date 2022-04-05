package com.byn.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: `sujinwang`
 * @Date: `2022/2/28 15:49`
 * @Version: 1.0
 * @Description:
 */
@Configuration
@ComponentScan(value = {"com.byn.web"})
@MapperScan(value = {"com.byn.web.mapper"})
public class CommonWebConfiguration {
}
