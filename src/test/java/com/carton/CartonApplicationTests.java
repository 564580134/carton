package com.carton;

import com.carton.bean.User;
import com.carton.controller.base.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class CartonApplicationTests {

    @Autowired
    UserController userController;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void contextLoads() {
        //userController.insertSelective(new User().setUserName("woshjo"));
//        //userController.updateSelectiveByPrimaryKey(new User().setUserId(9).setUserName("kebe"));
//        System.out.println(userController.selectAllByConditions().getData());
    }

}
