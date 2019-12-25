package com.carton.dao;

import com.carton.bean.User;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author Liu Runyong
 * @date 2019/12/23
 * @description UserMapper
 */
public interface UserMapper extends Mapper<User> {


    /**
     * 查询重复
     *
     * @param phone 电话
     * @return
     */
    int repeatUser(String phone);

    /**
     * 根据账号查询用户
     *
     * @param account 账号
     * @return user
     */
    User selectByAccount(String account);
}
