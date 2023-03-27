package com.kaoyan.institutions.service.impl;

import com.kaoyan.institutions.entity.Kaoyandata;
import com.kaoyan.institutions.mapper.KaoyandataMapper;
import com.kaoyan.institutions.service.KaoyandataService;
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
 * @since 2022-12-19
 */
@Service
public class KaoyandataServiceImpl extends ServiceImpl<KaoyandataMapper, Kaoyandata> implements KaoyandataService {
    @Autowired
    KaoyandataMapper kaoyandataMapper;

    @Override
    public List<Kaoyandata> SelectByLikeMajor(String major) {
        return kaoyandataMapper.selectByLikeMajor(major);
    }
}
