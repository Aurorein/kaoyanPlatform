package com.kaoyan.institutions.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaoyan.commonUtils.Res;
import com.kaoyan.institutions.entity.*;
import com.kaoyan.institutions.service.DzxxService;
import com.kaoyan.institutions.service.KaoyandataService;
import com.kaoyan.institutions.service.RjgcService;
import com.kaoyan.institutions.service.WlaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/institutions/major")
public class MajorController {


    @Autowired
    KaoyandataService kaoyandataService;

    @Autowired
    DzxxService dzxxService;

    @Autowired
    RjgcService rjgcService;

    @Autowired
    WlaqService wlaqService;

    @RequestMapping("/data")
    public Res GetData(@RequestParam("current") int current,@RequestParam(value = "pageSize") int pageSize,@RequestParam("school") String school,@RequestParam int year,@RequestParam int type,@RequestParam String major){
        if(major == "计算机"){
            Page<Kaoyandata> Page = new Page<>(current,pageSize);
            QueryWrapper<Kaoyandata> kaoyandataQueryWrapper = new QueryWrapper<>();
            kaoyandataQueryWrapper.eq("school_name",school);
            kaoyandataQueryWrapper.eq("the_year",year);
            if(type == 1){
                kaoyandataQueryWrapper.eq("xs_zs","xs");
            }else{
                kaoyandataQueryWrapper.eq("xs_zs","zs");
            }
            Page<Kaoyandata> page = kaoyandataService.page(Page, kaoyandataQueryWrapper);
            List<Kaoyandata> records = page.getRecords();
            long total = page.getTotal();

            return Res.ok().data("page",records).data("total",total);
        }else if(major == "软件工程"){
            Page<Rjgc> Page = new Page<>(current,pageSize);
            QueryWrapper<Rjgc> rjgcQueryWrapper = new QueryWrapper<>();
            rjgcQueryWrapper.eq("academy",school);
            Page<Rjgc> page = rjgcService.page(Page,rjgcQueryWrapper);
            List<Rjgc> records = page.getRecords();
            long total = page.getTotal();

            return Res.ok().data("page",records).data("total",total);
        }else if(major == "网络安全"){
            Page<Wlaq> Page = new Page<>(current,pageSize);
            QueryWrapper<Wlaq> wlaqQueryWrapper = new QueryWrapper<>();
            wlaqQueryWrapper.eq("academy",school);
            Page<Wlaq> page = wlaqService.page(Page,wlaqQueryWrapper);
            List<Wlaq> records = page.getRecords();
            long total = page.getTotal();
            return Res.ok().data("page",records).data("total",total);
        }else{
            Page<Dzxx> Page = new Page<>(current,pageSize);
            QueryWrapper<Dzxx> dzxxQueryWrapper = new QueryWrapper<>();
            dzxxQueryWrapper.eq("academy",school);
            Page<Dzxx> page = dzxxService.page(Page,dzxxQueryWrapper);
            List<Dzxx> records = page.getRecords();
            long total = page.getTotal();
            return Res.ok().data("page",records).data("total",total);
        }
    }

}
