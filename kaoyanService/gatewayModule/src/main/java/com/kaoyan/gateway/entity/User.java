package com.kaoyan.gateway.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author cxn
 * @since 2023-06-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id，唯一，自增")
    private Integer userId;

    @ApiModelProperty(value = "用户名，唯一")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "个性签名")
    private String personalSignature;

    @ApiModelProperty(value = "邮箱地址")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String phone;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;


}
