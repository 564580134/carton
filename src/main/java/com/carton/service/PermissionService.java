package com.carton.service;

import com.carton.bean.Permission;

import java.util.List;

/**
 * @author Liu Runyong
 * @date 2019/12/24
 * @description PermissionService
 */
public interface PermissionService {
    /**
     * 新增权限信息
     *
     * @param permission 权限信息
     */
    void insertSelective(Permission permission);


    /**
     * 编辑权限信息
     *
     * @param permission 权限信息
     */
    void updateSelectiveByPrimaryKey(Permission permission);

    /**
     * 根据主键删除权限信息
     *
     * @param id 主键
     */
    void deleteByPrimaryKey(Integer id);

    /**
     * 查询权限信息
     *
     * @param permissionName 权限名称
     * @return int
     */
    List<Permission> selectAllByConditions(String permissionName);

}
