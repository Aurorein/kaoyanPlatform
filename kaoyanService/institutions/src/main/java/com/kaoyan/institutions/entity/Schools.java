package com.kaoyan.institutions.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2023-01-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Schools对象", description="")
public class Schools implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String schoolName;

    private Integer is985;

    private Integer is211;

    private Integer df;

    private String img;

    private String region;


}
