package com.byn.article;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: `sujinwang`
 * @Date: `2022/2/27 2:57`
 * @Version: 1.0
 * @Description: 前台页面主要显示接口
 */
@Configuration
@ComponentScan(value = {"com.byn.article"})
@MapperScan(value = {"com.byn.article.mapper"})
public class ArticleConfiguration {

}
