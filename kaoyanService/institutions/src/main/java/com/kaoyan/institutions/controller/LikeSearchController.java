package com.kaoyan.institutions.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaoyan.commonUtils.Res;
import com.kaoyan.institutions.entity.*;
import com.kaoyan.institutions.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/institutions/like-search")
public class LikeSearchController {

    @Autowired
    SchooldetailService schooldetailService;
    @Autowired
    KaoyandataService kaoyandataService;

    @Autowired
    RjgcService rjgcService;

    @Autowired
    WlaqService wlaqService;

    @Autowired
    DzxxService dzxxService;

    @RequestMapping("/school")
    public Res SearchSchool(@RequestParam("current") int current,@RequestParam(value = "pageSize") int pageSize,@RequestParam String school_name){
        Page<Schooldetail> schooldetailPage = new Page<>(current, pageSize);
        QueryWrapper<Schooldetail> wrapper = new QueryWrapper<>();
        wrapper.like("school_name",school_name);
        schooldetailService.page(schooldetailPage, wrapper);
        List<Schooldetail> records = schooldetailPage.getRecords();
        long total = schooldetailPage.getTotal();
        return Res.ok().data("page",records).data("total",total);
    }

    @PostMapping("/major")
    public Res SearchMajor(@RequestParam("current") int current,@RequestParam(value = "pageSize") int pageSize,@RequestParam String major,@RequestParam String direction){
        if("jsj".equals(direction)){
            Page<Kaoyandata> Page = new Page<>(current,pageSize);
            QueryWrapper<Kaoyandata> kaoyandataQueryWrapper = new QueryWrapper<>();
            kaoyandataQueryWrapper.like("major_trend",major);
            kaoyandataService.page(Page, kaoyandataQueryWrapper);
            List<Kaoyandata> records = Page.getRecords();
            long total = Page.getTotal();

            return Res.ok().data("page",records).data("total",total);
        }else if("rjgc".equals(direction)){
            Page<Rjgc> Page = new Page<>(current,pageSize);
            QueryWrapper<Rjgc> rjgcQueryWrapper = new QueryWrapper<>();
            rjgcQueryWrapper.like("major_direction",major);
            rjgcService.page(Page,rjgcQueryWrapper);
            List<Rjgc> records = Page.getRecords();
            long total = Page.getTotal();

            return Res.ok().data("page",records).data("total",total);
        }else if("wlaq".equals(direction)){
            Page<Wlaq> Page = new Page<>(current,pageSize);
            QueryWrapper<Wlaq> wlaqQueryWrapper = new QueryWrapper<>();
            wlaqQueryWrapper.like("major_direction",major);
            wlaqService.page(Page,wlaqQueryWrapper);
            List<Wlaq> records = Page.getRecords();
            long total = Page.getTotal();
            return Res.ok().data("page",records).data("total",total);
        }else{
            Page<Dzxx> Page = new Page<>(current,pageSize);
            QueryWrapper<Dzxx> dzxxQueryWrapper = new QueryWrapper<>();
            dzxxQueryWrapper.like("major_direction",major);
            dzxxService.page(Page,dzxxQueryWrapper);
            List<Dzxx> records = Page.getRecords();
            long total = Page.getTotal();
            return Res.ok().data("page",records).data("total",total);
        }

    }
}
