package com.kaoyan.gateway.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色-权限关联关系表
 * </p>
 *
 * @author cxn
 * @since 2023-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysRolePermissionRelation对象", description="角色-权限关联关系表")
public class SysRolePermissionRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @ApiModelProperty(value = "权限id")
    private Integer permissionId;


}
