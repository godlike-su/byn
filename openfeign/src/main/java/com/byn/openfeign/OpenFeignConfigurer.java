package com.byn.openfeign;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: `sujinwang`
 * @Date: `2022/2/28 10:49`
 * @Version: 1.0
 * @Description: 用于公共类，调用各个模块的feign
 */
@Configurable
@EnableFeignClients
public class OpenFeignConfigurer {
}
