package com.kaoyan.institutions.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kaoyan.commonUtils.Res;
import com.kaoyan.institutions.entity.Schooldetail;
import com.kaoyan.institutions.entity.Schools;
import com.kaoyan.institutions.service.SchooldetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
 * @since 2023-01-06
 */
@RestController
@RequestMapping("/institutions/schooldetail")
public class SchooldetailController {

    @Autowired
    SchooldetailService service;


    @RequestMapping("getByPage/{current}/{limit}")
    public Res getByPage(@PathVariable int current, @PathVariable int limit){
        Page<Schooldetail> schoolsPage = new Page<>(current,limit);


        service.page(schoolsPage,null);

        List<Schooldetail> records = schoolsPage.getRecords();
        long total = schoolsPage.getTotal();
        return Res.ok().data("page",records).data("total",total);

    }


    @RequestMapping("getByPageForProvince/{current}/{limit}/{province}")
    public Res getByPageForProvince(@PathVariable int current,@PathVariable int limit,@PathVariable String province)
    {
        Page<Schooldetail> schoolsPage = new Page<>(current,limit);
        QueryWrapper<Schooldetail> wrapper = new QueryWrapper<>();
        wrapper.eq("region",province);
        service.page(schoolsPage,wrapper);
        List<Schooldetail> records = schoolsPage.getRecords();
        long total = schoolsPage.getTotal();
        return Res.ok().data("page",records).data("total",total);
    }

// type 0 985 | type 1 211 | type 2 双一流
    @RequestMapping("getByPageForProvinceAndType/{current}/{limit}/{province}/{type}")
    public Res getByPageForProvinceAndType(@PathVariable int current,@PathVariable int limit,@PathVariable String province,@PathVariable int type)
    {
        Page<Schooldetail> schoolsPage = new Page<>(current,limit);
        QueryWrapper<Schooldetail> wrapper = new QueryWrapper<>();
        wrapper.eq("region",province);
        if(type == 0){
            wrapper.eq("IS985",1);
        }else if(type == 1){
            wrapper.eq("IS211",1);
        }else{
            wrapper.eq("df",1);
        }
        service.page(schoolsPage,wrapper);
        List<Schooldetail> records = schoolsPage.getRecords();
        long total = schoolsPage.getTotal();
        return Res.ok().data("page",records).data("total",total);
    }

    @RequestMapping("getByPageForType/{current}/{limit}/{type}")
    public Res getByPageForType(@PathVariable int current,@PathVariable int limit,@PathVariable int type){
        Page<Schooldetail> schoolsPage = new Page<>(current,limit);
        QueryWrapper<Schooldetail> wrapper = new QueryWrapper<>();
        if(type == 0){
            wrapper.eq("IS985",1);
        }else if(type == 1){
            wrapper.eq("IS211",1);
        }else{
            wrapper.eq("df",1);
        }
        service.page(schoolsPage,wrapper);
        List<Schooldetail> records = schoolsPage.getRecords();
        long total = schoolsPage.getTotal();
        return Res.ok().data("page",records).data("total",total);
    }
}

