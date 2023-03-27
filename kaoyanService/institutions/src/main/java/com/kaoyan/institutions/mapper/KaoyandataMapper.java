package com.kaoyan.institutions.mapper;

import com.kaoyan.institutions.entity.Kaoyandata;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cxn
 * @since 2022-12-19
 */
@Repository
public interface KaoyandataMapper extends BaseMapper<Kaoyandata> {
    List<Kaoyandata> selectByLikeMajor(String major);
}
