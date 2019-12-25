package com.carton.serviceimpl;

import com.alibaba.druid.util.StringUtils;
import com.carton.bean.Permission;
import com.carton.bean.Role;
import com.carton.common.utils.CustomException;
import com.carton.common.utils.enums.ExceptionEnum;
import com.carton.dao.PermissionMapper;
import com.carton.service.PermissionService;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Liu Runyong
 * @date 2019/12/24
 * @description PermissionService
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void insertSelective(Permission permission) {
        if (0 == permissionMapper.insertSelective(permission)) {
            throw new CustomException(ExceptionEnum.MYSQL_INSERT_EXCEPTION);
        }
    }

    @Override
    public void updateSelectiveByPrimaryKey(Permission permission) {
        if (0 == permissionMapper.updateByPrimaryKeySelective(permission)) {
            throw new CustomException(ExceptionEnum.MYSQL_UPDATE_EXCEPTION);
        }
    }

    @Override
    public void deleteByPrimaryKey(Integer id) {
        if (0 == permissionMapper.deleteByPrimaryKey(id)) {
            throw new CustomException(ExceptionEnum.MYSQL_DELETE_EXCEPTION);
        }
    }

    @Override
    public List<Permission> selectAllByConditions(String permissionName) {
        Example example = new Example(Permission.class);
        if (!StringUtil.isEmpty(permissionName)) {
            example.createCriteria().andLike("permissionName", permissionName + "%");
        }
        return permissionMapper.selectByExample(example);

    }
}
