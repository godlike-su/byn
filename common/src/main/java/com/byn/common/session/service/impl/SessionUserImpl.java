package com.byn.common.session.service.impl;

import com.byn.common.session.entity.SessionUserDetail;
import com.github.pagehelper.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: `sujinwang`
 * @Date: `2022/2/28 10:32`
 * @Version: 1.0
 * @Description:
 */
@Service
public class SessionUserImpl implements com.byn.common.session.service.SessionUser {

    private static final SessionUserDetail ANONYMOUS_USER = new SessionUserDetail("-1", "anonymous", "10000");

    @Override
    public SessionUserDetail getSessionUser() {
        ServletRequestAttributes servletRequestAttributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        SessionUserDetail sessionUserDetail = null;
        if (servletRequestAttributes != null) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            sessionUserDetail = this.getUserbyLocation(request);
        }
        return sessionUserDetail == null ? ANONYMOUS_USER : sessionUserDetail;
    }

    private SessionUserDetail getUserbyLocation(HttpServletRequest request) {
        String userid = request.getHeader("userId");
        String username = request.getHeader("userName");
        String session = request.getHeader("session");
        if(StringUtil.isNotEmpty(userid) && StringUtil.isNotEmpty(username)) {
            return new SessionUserDetail(userid, username, session);
        }
        return null;
    }

}
