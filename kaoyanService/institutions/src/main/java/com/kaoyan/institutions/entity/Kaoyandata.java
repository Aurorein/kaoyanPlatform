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
 * @since 2022-12-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Kaoyandata对象", description="")
public class Kaoyandata implements Serializable {

    private static final long serialVersionUID = 1L;

    private String schoolName;

    private String departmentName;

    private String majorCode;

    private String majorName;

    private String majorTrend;

    private String politics;

    private String english;

    private String math;

    private String majorCourse;

    private Integer is985;

    private Integer is211;

    private String oj;

    private String provinceName;

    private String mainMajorCode;

    private String fullTime;

    private Integer admissionMajorCount;

    private Integer admissionTrendCount;

    private String comment;

    private Integer theYear;

    private String xsZs;


}
