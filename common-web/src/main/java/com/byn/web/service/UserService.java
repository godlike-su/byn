package com.byn.web.service;

import com.byn.common.session.entity.SessionUserDetail;
import com.byn.web.fo.UserFO;
import com.byn.web.vo.UserVO;

/**
 * @Author: `sujinwang`
 * @Date: `2022/4/5 22:14`
 * @Version: 1.0
 * @Description:
 */
public interface UserService {

    /**
     * 获取自己用户信息
     * @param sessionUser
     * @return
     */
    UserVO getMyInformation(SessionUserDetail sessionUser);

    /**
     * 获取其他用户信息
     * @param userFO
     * @return
     */
    UserVO getUserInformation(UserFO userFO);

    UserVO getUserById(String userId);

}
