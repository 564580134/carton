package com.carton.serviceimpl;

import com.carton.bean.Role;
import com.carton.bean.RolePermission;
import com.carton.common.utils.CustomException;
import com.carton.common.utils.enums.ExceptionEnum;
import com.carton.dao.RoleMapper;
import com.carton.dao.RolePermissionMapper;
import com.carton.service.RoleService;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author Liu Runyong
 * @date 2019/12/24
 * @description
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public void insertSelective(Role role) {
        if (0 == roleMapper.insertSelective(role)) {
            throw new CustomException(ExceptionEnum.MYSQL_INSERT_EXCEPTION);
        }
    }

    @Override
    public void updateSelectiveByPrimaryKey(Role role) {
        if (0 == roleMapper.updateByPrimaryKeySelective(role)) {
            throw new CustomException(ExceptionEnum.MYSQL_INSERT_EXCEPTION);
        }
    }

    @Override
    public void deleteByPrimaryKey(Integer id) {
        if (0 == roleMapper.deleteByPrimaryKey(id)) {
            throw new CustomException(ExceptionEnum.MYSQL_INSERT_EXCEPTION);
        }
    }

    @Override
    public List<Role> selectAllByConditions(String roleName) {
        Example example = new Example(Role.class);
        if (!StringUtil.isEmpty(roleName)) {
            example.createCriteria().andLike("roleName", roleName + "%");
        }
        return roleMapper.selectByExample(example);
    }

    @Override
    public void rolePermission(Integer roleId, List<Integer> permissionIds) {
        // 先删除角色所拥有的权限
        rolePermissionMapper.deleteByRoleId(roleId);
        // 重新添加角色权限
        permissionIds.forEach(permissionId -> {
            if (0 == rolePermissionMapper.insertSelective(new RolePermission().setRoleId(roleId).setPermissionId(permissionId))) {
                throw new CustomException(ExceptionEnum.MYSQL_INSERT_EXCEPTION);
            }
        });

    }

    @Override
    public List<Map<String, Object>> selectRolePermission(Integer roleId) {
        return rolePermissionMapper.selectRolePermission(roleId);
    }
}
