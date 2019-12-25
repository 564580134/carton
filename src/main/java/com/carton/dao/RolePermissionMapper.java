package com.carton.dao;

import com.carton.bean.RolePermission;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Liu Runyong
 * @date 2019/12/24
 * @description RolePermissionMapper
 */
public interface RolePermissionMapper extends Mapper<RolePermission> {

    /**
     * 编辑角色权限
     *
     * @param roleId 角色id
     * @return int
     */
    int deleteByRoleId(Integer roleId);


    /**
     * 查询角色权限
     *
     * @param roleId 角色id
     * @return
     */
    List<Map<String, Object>> selectRolePermission(Integer roleId);
}
