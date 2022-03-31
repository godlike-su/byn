package com.byn.gateway.dao;

import com.byn.gateway.entity.Role;
import com.byn.gateway.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author `sujinwang`
 * @version v1.0.0.0
 * @Date `2022/2/22 18:34`
 */
@Mapper
public interface UserDao {
    /**
     * 加载用户信息 by 用户名
     *
     * @param name 名字
     * @return {@link User}
     */
    User loadUserByUsername(String name);

    /**
     * 加载用户信息 by 用户id
     *
     * @param userId 名字
     * @return {@link User}
     */
    User loadUserByUserId(String userId);


    /**
     * 获取用户权限by Id
     *
     * @param id id
     * @return {@link List}<{@link Role}>
     */
    List<Role> getRolesByUid(String id);

    /**
     * 获取用户权限by Id 授权类型
     *
     * @param uId uId
     * @param rName rName
     * @return {@link List}<{@link Role}>
     */
    List<Role> getRolesByUidAndRname(@Param("uId") String uId,@Param("rName") String rName);
}
