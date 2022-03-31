package com.byn.client.service;

import com.byn.client.fo.UserFo;
import com.byn.openfeign.commonweb.fo.WXloginFo;
import com.result.BaseResult;
import com.result.MessageResult;

/**
 * @Author: `sujinwang`
 * @Date: `2022/3/28 0:11`
 * @Version: 1.0
 * @Description:
 */
public interface LoginService {

    MessageResult wxLogin(WXloginFo fo);

    /**
     * 修改自己的信息
     * @param fo
     * @return
     */
    BaseResult saveUser(UserFo fo);
}
