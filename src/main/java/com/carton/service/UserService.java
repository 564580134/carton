package com.carton.service;

import com.carton.bean.User;

import java.util.List;
import java.util.Map;

/**
 * @author Liu Runyong
 * @date 2019/12/23
 * @description UserService
 */
public interface UserService  {

    /**
     * 新增用户信息
     *
     * @param user 用户信息
     * @return int
     */
    void insertSelective(User user);


    /**
     * 查询重复
     *
     * @param phone 电话
     * @return int
     */
    boolean repeatUser(String phone);


    /**
     * 查询用户信息
     *
     * @param userName
     * @return int
     */
    List<User> selectAllByConditions(String userName);


    /**
     * 编辑用户信息
     *
     * @param user 用户信息
     * @return
     */
    void updateSelectiveByPrimaryKey(User user);


    /**
     * 根据主键删除用户信息
     *
     * @param ids 主键组
     */
    void deleteByPrimaryKey(List<Integer> ids);


    /**
     * 编辑用户组织
     *
     * @param userId          用户编号
     * @param organizationIds 组织编号
     */
    void userOrganization(Integer userId, List<String> organizationIds);

    /**
     * 查看用户组织
     *
     * @param userId 用户编号
     * @return list
     */
    List<Map<String, Object>> selectUserOrganization(Integer userId);


    /**
     * 编辑用户角色
     *
     * @param userId  用户编号
     * @param roleIds 角色编号
     */
    void userRole(Integer userId, List<Integer> roleIds);

    /**
     * 查看用户角色
     *
     * @param userId 用户编号
     * @return list
     */
    List<Map<String, Object>> selectUserRole(Integer userId);

    /**
     * 根据账号查询用户
     *
     * @param account 账号
     * @return user
     */
    User selectByAccount(String account);
}
