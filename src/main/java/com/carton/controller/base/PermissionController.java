package com.carton.controller.base;

import com.carton.bean.Permission;
import com.carton.common.utils.exception.ServerResponse;
import com.carton.service.PermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
@Api(tags = "权限模块", description = "PermissionController")
@RestController
@RequestMapping("/permission/")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation("新增角色信息")
    @PostMapping("insert")
    public ServerResponse insertSelective(@ModelAttribute Permission permission) {
        permissionService.insertSelective(permission);
        return ServerResponse.createBySuccessMessage("success");
    }


    @ApiOperation("查询角色信息")
    @GetMapping("select")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页显示条目数", paramType = "Integer"),
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "Integer"),
            @ApiImplicitParam(name = "permissionName", value = "权限名称", paramType = "string")
    })
    public ServerResponse selectAllByConditions(
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "roleName", required = false) String permissionName
    ) {
        if (pageNum > 0 && pageSize > 0) {
            // 设置分页
            PageHelper.startPage(pageNum, pageSize);
            // 查询
            return ServerResponse.createBySuccess(new PageInfo<>(permissionService.selectAllByConditions(permissionName)));
        } else {
            return ServerResponse.createBySuccess(permissionService.selectAllByConditions(null));
        }
    }


    @ApiOperation("编辑角色信息")
    @PutMapping("update")
    public ServerResponse updateSelectiveByPrimaryKey(@ModelAttribute Permission permission) {
        permissionService.updateSelectiveByPrimaryKey(permission);
        return ServerResponse.createBySuccessMessage("success");
    }


    @ApiOperation("删除角色")
    @DeleteMapping("delete")
    @ApiImplicitParam(name = "id", value = "主键", paramType = "Integer")
    public ServerResponse deleteByPrimaryKey(@NotBlank(message = "请选择删除对象") Integer id) {
        permissionService.deleteByPrimaryKey(id);
        return ServerResponse.createBySuccessMessage("success");
    }

}
