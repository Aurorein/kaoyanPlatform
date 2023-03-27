package com.kaoyan.institutions.mapper;

import com.kaoyan.institutions.entity.Kaoyandata;
import com.kaoyan.institutions.entity.Rjgc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cxn
 * @since 2022-12-30
 */
@Repository
public interface RjgcMapper extends BaseMapper<Rjgc> {
    List<Rjgc> selectByLikeMajor(String major);
}
