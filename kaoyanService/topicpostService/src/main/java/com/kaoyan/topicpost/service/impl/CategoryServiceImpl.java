package com.kaoyan.topicpost.service.impl;

import com.kaoyan.topicpost.entity.Category;
import com.kaoyan.topicpost.mapper.CategoryMapper;
import com.kaoyan.topicpost.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cxn
 * @since 2023-03-27
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
