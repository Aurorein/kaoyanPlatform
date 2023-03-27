package com.kaoyan.institutions.mapper;

import com.kaoyan.institutions.entity.Schooldetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cxn
 * @since 2023-01-06
 */
@Repository
public interface SchooldetailMapper extends BaseMapper<Schooldetail> {
    List<Schooldetail> selectByName(String school_name,int current,int pageSize);
}
