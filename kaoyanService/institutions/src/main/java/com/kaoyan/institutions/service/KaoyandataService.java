package com.kaoyan.institutions.service;

import com.kaoyan.institutions.entity.Kaoyandata;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cxn
 * @since 2022-12-19
 */
public interface KaoyandataService extends IService<Kaoyandata> {
    List<Kaoyandata> SelectByLikeMajor(String major);
}
