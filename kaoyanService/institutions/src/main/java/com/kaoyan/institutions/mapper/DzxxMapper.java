package com.kaoyan.institutions.mapper;

import com.kaoyan.institutions.entity.Dzxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kaoyan.institutions.entity.Kaoyandata;
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
public interface DzxxMapper extends BaseMapper<Dzxx> {
    List<Dzxx> selectByLikeMajor(String major);
}
