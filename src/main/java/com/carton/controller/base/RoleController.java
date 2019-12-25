package com.carton.controller.base;

import com.carton.bean.Role;
import com.carton.bean.User;
import com.carton.common.utils.exception.ServerResponse;
import com.carton.service.RoleService;
import com.carton.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Liu Runyong
 * @date 2019/12/24
 */
@Api(tags = "角色模块", description = "roleController")
@RestController
@RequestMapping("/role/")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("新增角色信息")
    @PostMapping("insert")
    public ServerResponse insertSelective(@ModelAttribute Role role) {
        roleService.insertSelective(role);
        return ServerResponse.createBySuccessMessage("success");
    }


    @ApiOperation("查询角色信息")
    @GetMapping("select")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页显示条目数", paramType = "Integer"),
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "Integer"),
            @ApiImplicitParam(name = "roleName", value = "角色名称", paramType = "string")
    })
    public ServerResponse selectAllByConditions(
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "roleName", required = false) String roleName
    ) {
        PageHelper.startPage(pageNum, pageSize);
        return ServerResponse.createBySuccess(new PageInfo<>(roleService.selectAllByConditions(roleName)));
    }


    @ApiOperation("编辑角色信息")
    @PutMapping("update")
    public ServerResponse updateSelectiveByPrimaryKey(@ModelAttribute Role role) {
        roleService.updateSelectiveByPrimaryKey(role);
        return ServerResponse.createBySuccessMessage("success");
    }


    @ApiOperation("删除角色")
    @DeleteMapping("delete")
    @ApiImplicitParam(name = "id", value = "主键", paramType = "Integer")
    public ServerResponse deleteByPrimaryKey(@NotBlank(message = "请选择删除对象") Integer id) {
        roleService.deleteByPrimaryKey(id);
        return ServerResponse.createBySuccessMessage("success");
    }

    @ApiOperation("编辑角色权限")
    @PostMapping("permission")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "Integer"),
            @ApiImplicitParam(name = "permissionIds", value = "权限id", required = true, paramType = "Integer[]"),
    })
    public ServerResponse rolePermission(@NotBlank(message = "请选择删除对象") Integer roleId,
                                         @Size(min = 1, message = "请选择权限信息") List<Integer> permissionIds) {
        roleService.rolePermission(roleId, permissionIds);
        return ServerResponse.createBySuccessMessage("success");
    }

    @ApiOperation("查询角色权限")
    @PostMapping("selectRolePermission")
    @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "Integer")
    public ServerResponse selectRolePermission(@NotBlank(message = "请选择删除对象") Integer roleId) {
        return ServerResponse.createBySuccess(roleService.selectRolePermission(roleId));
    }
}
