package com.byn.common;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: `sujinwang`
 * @Date: `2022/2/28 10:49`
 * @Version: 1.0
 * @Description:
 */
@Configurable
@ComponentScan(
        basePackages = {"com.byn.common.exception", "com.byn.common.session"}
)
public class CommonConfigurer {
}
