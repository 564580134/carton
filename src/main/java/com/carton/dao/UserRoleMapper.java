package com.carton.dao;

import com.carton.bean.UserRole;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Liu Runyong
 * @date 2019/12/24
 * @description UserRoleMapper
 */
public interface UserRoleMapper extends Mapper<UserRole> {

    /**
     * 删除用户角色
     *
     * @param userId 用户编号
     * @return int
     */
    int deleteByUserId(Integer userId);

    /**
     * 查询用户角色
     *
     * @param userId
     * @return
     */
    List<Map<String, Object>> selectUserRole(Integer userId);
}
