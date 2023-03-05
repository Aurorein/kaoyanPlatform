package com.kaoyan.institutions.entity;

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
 * @since 2022-12-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Wlaq对象", description="")
public class Wlaq implements Serializable {

    private static final long serialVersionUID = 1L;

    private String province;

    private String academy;

    private String institute;

    private String majorNum;

    private String minorNum;

    private String major;

    private String majorDirection;

    private String pattern;

    private String politics;

    private String english;

    private String math;

    private String proCourse;

    private String majorNums;

    private String specityNums;

    private String remark;


}
