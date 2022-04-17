package com.byn.openfeign.commonweb;

import com.byn.common.util.ObjectTransform;
import com.byn.web.entity.User;
import com.byn.web.fo.UserFO;
import com.byn.web.service.UserService;
import com.byn.web.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: `sujinwang`
 * @Date: `2022/4/17 21:34`
 * @Version: 1.0
 * @Description:
 */
@Service
public class UserFeignImpl implements UserFeign{

    @Autowired
    private UserService userService;

    @Override
    public UserVO getUserById(String usreId) {
        return userService.getUserById(usreId);
    }



}
