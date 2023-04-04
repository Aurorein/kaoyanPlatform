package com.kaoyan.topicpost.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaoyan.commonUtils.Res;
import com.kaoyan.topicpost.domain.PostData;
import com.kaoyan.topicpost.domain.TopicData;
import com.kaoyan.topicpost.entity.Post;
import com.kaoyan.topicpost.entity.Topic;
import com.kaoyan.topicpost.entity.User;
import com.kaoyan.topicpost.service.PostService;
import com.kaoyan.topicpost.service.TopicService;
import com.kaoyan.topicpost.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cxn
 * @since 2023-03-27
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/community/topic")
public class TopicController {

    @Autowired
    TopicService topicService;

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @GetMapping("/cid/{id}")
    public Res getByCId(@PathVariable int id,
                        @RequestParam("current") int current,
                        @RequestParam("pageSize") int pageSize){
        Page<Topic> topicPage = new Page<>(current,pageSize);
        QueryWrapper<Topic> topicWrapper = new QueryWrapper<>();
        topicWrapper.eq("cid",id);
        topicService.page(topicPage,topicWrapper);
        List<Topic> records = topicPage.getRecords();
        long total = topicPage.getTotal();
        List<TopicData> topicDataList = new ArrayList<>();
        for (Topic topic: records){
            Integer topicId = topic.getId();
            Integer userId = topic.getUserId();
            QueryWrapper<Post> postQueryWrapper = new QueryWrapper<>();
            postQueryWrapper.eq("topic_id",topicId);
            List<Post> postList = postService.list(postQueryWrapper);

            QueryWrapper<User> userWrapper = new QueryWrapper<User>();
            userWrapper.eq("user_id",userId);
            List<User> topicUser = userService.list(userWrapper);
            List<PostData> postDataList = new ArrayList<>();
            TopicData topicData = new TopicData(topic, topicUser,null);

            for(Post post:postList){
                Integer postUserId = post.getUserId();
                QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
                userQueryWrapper.eq("user_id",postUserId);

                List<User> users = userService.list(userQueryWrapper);
                PostData postData = new PostData(post, users);
                postDataList.add(postData);
            }

            topicData.setPostData(postDataList);
            topicDataList.add(topicData);
        }
        return Res.ok().data("data",topicDataList).data("total",total);

    }



}

