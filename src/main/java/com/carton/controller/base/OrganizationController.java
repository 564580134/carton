package com.carton.controller.base;

import com.carton.bean.Organization;
import com.carton.bean.Role;
import com.carton.common.utils.exception.ServerResponse;
import com.carton.service.OrganizationService;
import com.carton.service.RoleService;
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
@Api(tags = "组织模块", description = "OrganizationController")
@RestController
@RequestMapping("/organization/")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @ApiOperation("新增组织信息")
    @PostMapping("insert")
    public ServerResponse insertSelective(@ModelAttribute Organization organization) {
        organizationService.insertSelective(organization);
        return ServerResponse.createBySuccessMessage("success");
    }


    @ApiOperation("查询组织信息")
    @GetMapping("select")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页显示条目数", paramType = "Integer"),
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "Integer"),
            @ApiImplicitParam(name = "organizationName", value = "组织名称", paramType = "string")
    })
    public ServerResponse selectAllByConditions(
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "roleName", required = false) String organizationName) {
        PageHelper.startPage(pageNum, pageSize);
        return ServerResponse.createBySuccess(new PageInfo<>(organizationService.selectAllByConditions(organizationName)));
    }


    @ApiOperation("编辑组织信息")
    @PutMapping("update")
    public ServerResponse updateSelectiveByPrimaryKey(@ModelAttribute Organization organization) {
        organizationService.updateSelectiveByPrimaryKey(organization);
        return ServerResponse.createBySuccessMessage("success");
    }


    @ApiOperation("删除组织")
    @DeleteMapping("delete")
    @ApiImplicitParam(name = "id", value = "主键", paramType = "Integer")
    public ServerResponse deleteByPrimaryKey(@NotBlank(message = "请选择删除对象") String id) {
        organizationService.deleteByPrimaryKey(id);
        return ServerResponse.createBySuccessMessage("success");
    }
}
