package com.byn.openfeign.commonweb;

import com.byn.web.vo.UserVO;

/**
 * @Author: `sujinwang`
 * @Date: `2022/4/17 21:43`
 * @Version: 1.0
 * @Description:
 */
public interface UserFeign {
    UserVO getUserById(String usreId);
}
