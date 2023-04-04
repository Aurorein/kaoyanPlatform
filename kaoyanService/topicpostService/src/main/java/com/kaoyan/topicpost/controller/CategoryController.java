package com.kaoyan.topicpost.controller;


import com.kaoyan.commonUtils.Res;
import com.kaoyan.topicpost.service.CategoryService;
import com.kaoyan.topicpost.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/api/community/category")
public class CategoryController {


    @Autowired
    CategoryService categoryService;

    @GetMapping("/all")
    public Res getGategory(){
        return Res.ok().data("data",categoryService.list());
    }

}

