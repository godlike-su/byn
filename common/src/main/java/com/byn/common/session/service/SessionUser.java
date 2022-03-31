package com.byn.common.session.service;


import com.byn.common.session.entity.SessionUserDetail;

/**
 * @Author: `sujinwang`
 * @Date: `2022/2/28 10:30`
 * @Version: 1.0
 * @Description: 获取session里面的用户
 */
public interface SessionUser {
    SessionUserDetail getSessionUser();


}
