package com.kaoyan.institutions.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.kaoyan.commonUtils.Res;
import com.kaoyan.institutions.entity.Jsj;
import com.kaoyan.institutions.service.JsjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cxn
 * @since 2022-12-30
 */
@RestController
@RequestMapping("/institutions/jsj")
public class JsjController {
    @Autowired
    JsjService jsjService;

    @RequestMapping("getBySchool/{academy}")
    public Res getBySchool(@PathVariable String academy){
        QueryWrapper<Jsj> jsjQueryWrapper = new QueryWrapper<>();
        jsjQueryWrapper.eq("academy",academy);
        List<Jsj> list = jsjService.list(jsjQueryWrapper);
        return Res.ok().data("list",list);
    }








}

