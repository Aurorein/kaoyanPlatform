package com.kaoyan.institutions.service;

import com.kaoyan.institutions.entity.Wlaq;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cxn
 * @since 2022-12-30
 */
public interface WlaqService extends IService<Wlaq> {
    List<Wlaq> selectByLikeMajor(String major);
}
