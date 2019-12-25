package com.carton.service;

import com.carton.bean.Organization;

import java.util.List;

/**
 * @author Liu Runyong
 * @date 2019/12/24
 * @description
 */
public interface OrganizationService {

    /**
     * 新增组织信息
     *
     * @param organization 组织信息
     */
    void insertSelective(Organization organization);


    /**
     * 编辑组织信息
     *
     * @param organization 组织信息
     */
    void updateSelectiveByPrimaryKey(Organization organization);

    /**
     * 根据主键删除组织信息
     *
     * @param id 主键
     */
    void deleteByPrimaryKey(String id);

    /**
     * 查询组织信息
     *
     * @return int
     * @param organizationName
     */
    List<Organization> selectAllByConditions(String organizationName);

}
