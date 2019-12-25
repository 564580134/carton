package com.carton.controller.sso;

import com.carton.bean.User;
import com.carton.common.utils.exception.ServerResponse;
import com.carton.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * @author Liu Runyong
 * @date 2019/12/24
 */

@Api(tags = "登录模块", description = "ssoLoginController")
@RestController
@RequestMapping("/sso/")
public class SsoLoginController {

    @Autowired
    private UserService userService;


    @ApiOperation("新增用户信息")
    @PostMapping("login")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "account", name = "账号", required = true, paramType = "String"),
            @ApiImplicitParam(value = "password", name = "账号", required = true, paramType = "String"),
    })
    public ServerResponse insertSelective(
            @NotBlank(message = "请输入账号") @RequestParam(value = "account") String account,
            @NotBlank(message = "请输入密码") @RequestParam(value = "password") String password
    ) {
        // 查询相关信息
        User user = userService.selectByAccount(account);
        if (password.equals(user.getPassword())) {
            return ServerResponse.createBySuccess();
        } else {
            return ServerResponse.createByErrorMessage("账号或密码错误");
        }
    }

}
