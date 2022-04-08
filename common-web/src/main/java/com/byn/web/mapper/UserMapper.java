package com.byn.web.mapper;

import com.byn.web.entity.Role;
import com.byn.web.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author `sujinwang`
 * @version v1.0.0.0
 * @Date `2022/2/22 18:34`
 */
@Mapper
public interface UserMapper {
    /**
     * 加载用户信息 by 用户名
     *
     * @param name 名字
     * @return {@link User}
     */
    User loadUserByUsername(String name);

    /**
     * 获取用户权限by Id
     *
     * @param id id
     * @return {@link List}<{@link Role}>
     */
    List<Role> getRolesByUid(String id);

    /**
     * 加载用户信息 根据 微信unionid
     *
     * @param wxUnionid 名字
     * @return {@link User}
     */
    User loadUserByWxUnionid(String wxUnionid);

    /**
     * 加载用户信息 根据 wxAppid
     * @param wxAppid
     * @return
     */
    User loadUserByWxAppid(String wxAppid);

    int register(User user);

    /**
     * 查找单个用户信息，根据传入的查找条件
     * @param user
     * @return
     */
    User getUser(User user);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    int saveUser(User user);

}
