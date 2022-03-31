package com.byn.gateway.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author `sujinwang`
 * @version v1.0.0.0
 * @Date `2022/2/22 18:27`
 */
@Data
public class User {
    private String userId;
    private String userName;
    private String password;
    private List<Role> roles = new ArrayList<>();
    public User() {

    }
    public User(String userId, String username) {
        this.userId = userId;
        this.userName = username;
    }
}