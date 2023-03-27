package com.kaoyan.topicpost.domain;


import com.kaoyan.topicpost.entity.Post;
import com.kaoyan.topicpost.entity.Topic;
import com.kaoyan.topicpost.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TopicData {

    Topic topic;
    List<User> topicUser;
    List<PostData> postData;

}
