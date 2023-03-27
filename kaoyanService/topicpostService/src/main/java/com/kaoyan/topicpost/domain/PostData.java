package com.kaoyan.topicpost.domain;


import com.kaoyan.topicpost.entity.Post;
import com.kaoyan.topicpost.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class PostData {
    Post post;
    List<User> user;
}
