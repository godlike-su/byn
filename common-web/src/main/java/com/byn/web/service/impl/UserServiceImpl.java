package com.byn.web.service.impl;

import com.byn.common.session.entity.SessionUserDetail;
import com.byn.common.util.ObjectTransform;
import com.byn.web.entity.User;
import com.byn.web.fo.UserFO;
import com.byn.web.mapper.UserMapper;
import com.byn.web.service.UserService;
import com.byn.web.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: `sujinwang`
 * @Date: `2022/4/5 22:15`
 * @Version: 1.0
 * @Description:
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserVO getMyInformation(SessionUserDetail sessionUser) {
        User user = new User();
        user.setUserId(sessionUser.getUserId());
        User u = userMapper.getUser(user);
        return ObjectTransform.transform(u, UserVO.class);
    }

    @Override
    public UserVO getUserInformation(UserFO userFO) {
        User user = ObjectTransform.transform(userFO, User.class);
        User u = userMapper.getUser(user);
        return ObjectTransform.transform(u, UserVO.class);
    }

    @Override
    public UserVO getUserById(String userId) {
        User user = new User();
        user.setUserId(userId);
        User u = userMapper.getUser(user);
        return ObjectTransform.transform(u, UserVO.class);
    }


}
