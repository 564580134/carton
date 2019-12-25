package com.carton.serviceimpl;

import com.carton.bean.Organization;
import com.carton.common.utils.CustomException;
import com.carton.common.utils.enums.ExceptionEnum;
import com.carton.dao.OrganizationMapper;
import com.carton.service.OrganizationService;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author Liu Runyong
 * @date 2019/12/24
 * @description
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;


    @Override
    public void insertSelective(Organization organization) {
        if (0 == organizationMapper.insertSelective(organization)) {
            throw new CustomException(ExceptionEnum.MYSQL_INSERT_EXCEPTION);
        }
    }

    @Override
    public void updateSelectiveByPrimaryKey(Organization organization) {
        if (0 == organizationMapper.updateByPrimaryKeySelective(organization)) {
            throw new CustomException(ExceptionEnum.MYSQL_UPDATE_EXCEPTION);
        }
    }

    @Override
    public void deleteByPrimaryKey(String id) {
        if (0 == organizationMapper.deleteByPrimaryKey(id)) {
            throw new CustomException(ExceptionEnum.MYSQL_DELETE_EXCEPTION);
        }
    }

    @Override
    public List<Organization> selectAllByConditions(String organizationName) {
        Example example = new Example(Organization.class);
        if (!StringUtil.isEmpty(organizationName)) {
            example.createCriteria().andLike("organizationName", organizationName + "%");
        }
        return organizationMapper.selectByExample(example);
    }
}
