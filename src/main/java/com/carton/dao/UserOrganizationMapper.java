package com.carton.dao;

import com.carton.bean.UserOrganization;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Liu Runyong
 * @date 2019/12/24
 * @description UserOrganizationMapper
 */
public interface UserOrganizationMapper extends Mapper<UserOrganization> {


    /**
     * 根据用户删除用户组织
     *
     * @param userId 用户编号
     * @return int
     */
    int deleteByUserId(Integer userId);


    /**
     * 查看用户组织
     *
     * @param userId 用户编号
     * @return
     */
    List<Map<String, Object>> selectUserOrganization(Integer userId);
}
