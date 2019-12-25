package com.carton.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Liu Runyong
 * @date 2019/12/24
 * @description 组织信息
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "organization", description = "组织信息")
public class Organization implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键(新增#,更新*)")
    private Integer organizationId;

    @ApiModelProperty(value = "角色名称")
    private String organizationName;

    @ApiModelProperty(value = "角色名称")
    private String organizationDescription;

    @ApiModelProperty(value = "上级编号")
    private String superiorId;


}
