package com.byn.openfeign.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @Author: `sujinwang`
 * @Date: `2022/4/20 0:32`
 * @Version: 1.0
 * @Description:
 */
@Component
@FeignClient(name="byn-client")
public interface ClientFeign {
//    @PostMapping("${byn.mapping.name}/client/")
}
