package com.kaoyan.institutions.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author cxn
 * @since 2023-01-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("schoolDetail")
@ApiModel(value="Schooldetail对象", description="")
public class Schooldetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private String schoolName;

    private String region;

    private String subjection;

    private String graduateSchool;

    private String selfMarking;

    private String code;

    @TableField("IS211")
    private String is211;

    @TableField("IS985")
    private String is985;

    @TableField("AB")
    private String ab;

    private String df;

    private String doctorStation;


}
