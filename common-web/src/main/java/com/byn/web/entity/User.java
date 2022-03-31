package com.byn.web.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author `sujinwang`
 * @version v1.0.0.0
 * @Date `2022/2/22 18:27`
 */
@Data
public class User {
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    private String sex;
    private String qqid;
    private String qq;
    private String qqFlag;
    private String qqName;
    private String email;
    private String emailFlag;
    private String phone;
    private String phoneFlag;
    /**
     * 头像
     */
    private String thumb;
    /**
     * wx appid
     */
    private String wxAppid;
    /**
     * wx unionid
     */
    private String wxUnionid;
    /**
     * 创建时间
     */
    private Date timeCreate;
    /**
     * 更改时间
     */
    private Date timeUpdate;
    /**
     * 最后一次登录时间
     */
    private Date timeLogin;
    private String studentID;
    private String school;
    /**
     * 职业
     */
    private String profession;
    /**
     * 个人介绍
     */
    private String introduction;
    /**
     * 启用
     */
    private Boolean enabled;
    /**
     * 账户不过期
     */
    private Boolean accountNonExpired;
    /**
     * 非锁定账户
     */
    private Boolean accountNonLocked;
    /**
     * 凭证不过期
     */
    private Boolean credentialsNonExpired;

    /**
     * 角色
     */
    private List<Role> roles = new ArrayList<>();

    public User() {

    }

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}