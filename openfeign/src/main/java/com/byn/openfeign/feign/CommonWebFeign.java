package com.byn.openfeign.feign;

import com.byn.openfeign.fo.UserFO;
import com.byn.openfeign.vo.UserVO;
import com.result.SingleResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: `sujinwang`
 * @Date: `2022/3/20 2:30`
 * @Version: 1.0
 * @Description:
 */
@Component
@FeignClient(name="byn-web-common")
public interface CommonWebFeign {
    /**
     * 获取其他的用户信息信息
     * @return
     */
    @PostMapping("${byn.mapping.name}/web/user/getUserInformation")
    SingleResult<UserVO> getUserInformation(@RequestBody UserFO userFO);

}
