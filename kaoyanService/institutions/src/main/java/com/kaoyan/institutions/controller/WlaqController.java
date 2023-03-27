package com.kaoyan.institutions.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.kaoyan.commonUtils.Res;
import com.kaoyan.institutions.entity.Wlaq;
import com.kaoyan.institutions.service.WlaqService;
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
@RequestMapping("/institutions/wlaq")
public class WlaqController {

    @Autowired
    WlaqService wlaqService;

    @RequestMapping("getBySchool/{academy}")
    public Res getBySchool(@PathVariable String academy){
        QueryWrapper<Wlaq> wlaqQueryWrapper = new QueryWrapper<>();
        wlaqQueryWrapper.eq("academy",academy);
        List<Wlaq> list = wlaqService.list(wlaqQueryWrapper);
        return Res.ok().data("list",list);
    }

}

