package com.carton.service;

import com.carton.bean.Role;

import java.util.List;
import java.util.Map;

/**
 * @author Liu Runyong
 * @date 2019/12/24
 * @description
 */
public interface RoleService {

    /**
     * 新增角色信息
     *
     * @param role 角色信息
     */
    void insertSelective(Role role);


    /**
     * 编辑权限信息
     *
     * @param role 角色信息
     */
    void updateSelectiveByPrimaryKey(Role role);

    /**
     * 根据主键删除角色信息
     *
     * @param id 主键
     */
    void deleteByPrimaryKey(Integer id);

    /**
     * 查询角色信息
     *
     * @param roleName 角色名称
     * @return list
     */
    List<Role> selectAllByConditions(String roleName);


    /**
     * 编辑角色权限
     *
     * @param roleId        角色id
     * @param permissionIds 权限id
     */
    void rolePermission(Integer roleId, List<Integer> permissionIds);

    /**
     * 查询当前角色的权限信息
     *
     * @param roleId 角色id
     * @return
     */
    List<Map<String, Object>> selectRolePermission(Integer roleId);
}
