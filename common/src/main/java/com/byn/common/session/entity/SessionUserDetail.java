package com.byn.common.session.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author `sujinwang`
 * @version v1.0.0.0
 * @Date `2022/2/22 18:27`
 */
@Data
public class SessionUserDetail {
    private String userId;
    private String userName;
    private String password;
    private Boolean enabled;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private List<Role> roles = new ArrayList<>();
    private String session;

    public SessionUserDetail() {

    }

    public SessionUserDetail(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public SessionUserDetail(String userId, String userName, String session) {
        this.userId = userId;
        this.userName = userName;
        this.session = session;
    }
}