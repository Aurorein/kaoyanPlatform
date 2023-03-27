package com.kaoyan.institutions.service.impl;

import com.kaoyan.institutions.entity.Wlaq;
import com.kaoyan.institutions.mapper.WlaqMapper;
import com.kaoyan.institutions.service.WlaqService;
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
public class WlaqServiceImpl extends ServiceImpl<WlaqMapper, Wlaq> implements WlaqService {
    @Autowired
    WlaqMapper wlaqMapper;

    @Override
    public List<Wlaq> selectByLikeMajor(String major) {
        return wlaqMapper.selectByLikeMajor(major);
    }
}
