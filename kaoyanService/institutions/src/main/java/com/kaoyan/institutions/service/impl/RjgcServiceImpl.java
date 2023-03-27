package com.kaoyan.institutions.service.impl;

import com.kaoyan.institutions.entity.Rjgc;
import com.kaoyan.institutions.mapper.RjgcMapper;
import com.kaoyan.institutions.service.RjgcService;
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
public class RjgcServiceImpl extends ServiceImpl<RjgcMapper, Rjgc> implements RjgcService {
    @Autowired
    RjgcMapper rjgcMapper;


    @Override
    public List<Rjgc> selectByLikeSearch(String major) {
        return rjgcMapper.selectByLikeMajor(major);
    }
}
