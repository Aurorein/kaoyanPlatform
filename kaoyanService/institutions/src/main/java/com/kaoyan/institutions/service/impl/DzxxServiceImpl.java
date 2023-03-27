package com.kaoyan.institutions.service.impl;

import com.kaoyan.institutions.entity.Dzxx;
import com.kaoyan.institutions.mapper.DzxxMapper;
import com.kaoyan.institutions.service.DzxxService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cxn
 * @since 2022-12-30
 */
@Service
public class DzxxServiceImpl extends ServiceImpl<DzxxMapper, Dzxx> implements DzxxService {
    @Autowired
    DzxxMapper dzxxMapper;

    @Override
    public List<Dzxx> selectByLikeMajor(String major) {
        return dzxxMapper.selectByLikeMajor(major);
    }
}
