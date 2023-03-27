package com.kaoyan.topicpost.service.impl;

import com.kaoyan.topicpost.entity.Post;
import com.kaoyan.topicpost.mapper.PostMapper;
import com.kaoyan.topicpost.service.PostService;
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
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

}
