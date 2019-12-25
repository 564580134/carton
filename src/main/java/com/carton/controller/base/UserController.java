package com.carton.controller.base;

import com.carton.bean.User;
import com.carton.common.utils.exception.ServerResponse;
import com.carton.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Liu Runyong
 * @date 2019/12/23
 */
@Api(tags = "用户信息", description = "userController")
@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @ApiOperation("新增用户信息")
    @PostMapping("insert")
    public ServerResponse insertSelective(@ModelAttribute User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (userService.repeatUser(user.getPhone())) {
            userService.insertSelective(user);
            return ServerResponse.createBySuccessMessage("success");
        } else {
            return ServerResponse.createByErrorMessage("手机号重复");
        }
    }


    @ApiOperation("查询用户信息")
    @GetMapping("select")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页显示条目数", paramType = "Integer"),
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "Integer"),
            @ApiImplicitParam(name = "userName", value = "角色名称", paramType = "string")
    })
    public ServerResponse selectAllByConditions(
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "userName", required = false) String userName
    ) {
        PageHelper.startPage(pageNum, pageSize);
        return ServerResponse.createBySuccess(new PageInfo<>(userService.selectAllByConditions(userName)));
    }


    @ApiOperation("编辑用户信息")
    @PutMapping("update")
    public ServerResponse updateSelectiveByPrimaryKey(@ModelAttribute User user) {
        userService.updateSelectiveByPrimaryKey(user);
        return ServerResponse.createBySuccessMessage("success");
    }


    @ApiOperation("删除用户")
    @DeleteMapping("delete")
    @ApiImplicitParam(name = "ids", value = "主键集合", paramType = "List<Integer>")
    public ServerResponse deleteByPrimaryKey(@Size(min = 1, message = "请选择删除对象") List<Integer> ids) {
        userService.deleteByPrimaryKey(ids);
        return ServerResponse.createBySuccessMessage("success");
    }

    @ApiOperation("用户组织")
    @PostMapping("organization")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "Integer"),
            @ApiImplicitParam(name = "organizationIds", value = "组织id", paramType = "List<String>")
    })
    public ServerResponse userOrganization(@NotBlank(message = "请选择用户") Integer userId, @Size(min = 1, message = "请选择组织") List<String> organizationIds) {
        userService.userOrganization(userId, organizationIds);
        return ServerResponse.createBySuccessMessage("success");
    }

    @ApiOperation("查看用户组织")
    @PostMapping("selectUserOrganization")
    @ApiImplicitParam(name = "userId", value = "用户id", paramType = "Integer")
    public ServerResponse selectUserOrganization(@NotBlank(message = "请选择用户") Integer userId) {
        return ServerResponse.createBySuccess(userService.selectUserOrganization(userId));
    }

    @ApiOperation("用户角色")
    @DeleteMapping("role")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "Integer"),
            @ApiImplicitParam(name = "roleId", value = "角色id", paramType = "List<Integer>")
    })
    public ServerResponse userRole(@NotBlank(message = "请选择用户") Integer userId, @Size(min = 1, message = "请选择角色") List<Integer> roleIds) {
        userService.userRole(userId, roleIds);
        return ServerResponse.createBySuccessMessage("success");
    }

    @ApiOperation("查看用户角色")
    @PostMapping("selectUserRole")
    @ApiImplicitParam(name = "userId", value = "用户id", paramType = "Integer")
    public ServerResponse selectUserRole(@NotBlank(message = "请选择用户") Integer userId) {
        return ServerResponse.createBySuccess(userService.selectUserRole(userId));
    }

}
