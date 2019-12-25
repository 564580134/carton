package com.carton.serviceimpl;

import com.carton.bean.User;
import com.carton.bean.UserOrganization;
import com.carton.bean.UserRole;
import com.carton.common.utils.CustomException;
import com.carton.common.utils.enums.ExceptionEnum;
import com.carton.dao.UserMapper;
import com.carton.dao.UserOrganizationMapper;
import com.carton.dao.UserRoleMapper;
import com.carton.service.UserService;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;


import java.util.List;
import java.util.Map;

/**
 * @author Liu Runyong
 * @date 2019/12/23
 * @description UserServiceImpl
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserOrganizationMapper userOrganizationMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public void insertSelective(User user) {
        if (0 == userMapper.insertSelective(user)) {
            throw new CustomException(ExceptionEnum.MYSQL_INSERT_EXCEPTION);
        }
    }

    @Override
    public boolean repeatUser(String phone) {
        return userMapper.repeatUser(phone) < 1;
    }

    @Override
    public List<User> selectAllByConditions(String userName) {
        Example example = new Example(User.class);
        if (!StringUtil.isEmpty(userName)) {
            example.createCriteria().andLike("userName", userName + "%");
        }
        return userMapper.selectByExample(example);
    }

    @Override
    public void updateSelectiveByPrimaryKey(User user) {
        if (0 == userMapper.updateByPrimaryKeySelective(user)) {
            throw new CustomException(ExceptionEnum.MYSQL_UPDATE_EXCEPTION);
        }
    }

    @Override
    public void deleteByPrimaryKey(List<Integer> ids) {
        ids.forEach(id -> {
            if (userMapper.deleteByPrimaryKey(id) == 0) {
                throw new CustomException(ExceptionEnum.MYSQL_DELETE_EXCEPTION);
            }
        });
    }

    @Override
    public void userOrganization(Integer userId, List<String> organizationIds) {
        // 删除用户相关组织
        userOrganizationMapper.deleteByUserId(userId);
        // 更新用户组织
        organizationIds.forEach(organizationId -> {
            if (0 == userOrganizationMapper.insertSelective(new UserOrganization().setUserId(userId).setOrganizationId(organizationId))) {
                throw new CustomException(ExceptionEnum.MYSQL_INSERT_EXCEPTION);
            }
        });
    }

    @Override
    public List<Map<String, Object>> selectUserOrganization(Integer userId) {
        return userOrganizationMapper.selectUserOrganization(userId);
    }

    @Override
    public void userRole(Integer userId, List<Integer> roleIds) {
        // 删除用户相关组织
        userRoleMapper.deleteByUserId(userId);
        // 更新用户组织
        roleIds.forEach(roleId -> {
            if (0 == userRoleMapper.insertSelective(new UserRole().setUserId(userId).setRoleId(roleId))) {
                throw new CustomException(ExceptionEnum.MYSQL_INSERT_EXCEPTION);
            }
        });
    }

    @Override
    public List<Map<String, Object>> selectUserRole(Integer userId) {
        return userRoleMapper.selectUserRole(userId);
    }

    @Override
    public User selectByAccount(String account) {
        return userMapper.selectByAccount(account);
    }

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        return userMapper.selectByAccount(account);
    }
}
