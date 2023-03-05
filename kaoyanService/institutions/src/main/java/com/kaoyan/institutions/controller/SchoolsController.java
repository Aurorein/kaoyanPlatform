package com.kaoyan.institutions.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.kaoyan.commonUtils.Res;
import com.kaoyan.institutions.entity.Schools;
import com.kaoyan.institutions.service.SchoolsService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2023-01-02
 */
@RestController
@RequestMapping("/institutions/schools")
public class SchoolsController {

    @Autowired
    SchoolsService schoolsService;

    @RequestMapping("gets")
    public Res gets(){
        return Res.ok().data("data",schoolsService.list(null));
    }

    @RequestMapping("schoolImg/{schoolName}")
    public Res getIcon(@PathVariable String schoolName) {
        return Res.ok().data("img","47.104.158.201:8001/"+schoolName+".jpg");
    }

    @RequestMapping("getByPage/{current}/{limit}")
    public Res getByPage(@PathVariable int current, @PathVariable int limit){
        Page<Schools> schoolsPage = new Page<>(current,limit);


        schoolsService.page(schoolsPage,null);

        List<Schools> records = schoolsPage.getRecords();
        long total = schoolsPage.getTotal();
        return Res.ok().data("page",records).data("total",total);

    }

    @RequestMapping("getByPageForProvince/{current}/{limit}/{province}")
    public Res getByPageForProvince(@PathVariable int current,@PathVariable int limit,@PathVariable String province)
    {
        Page<Schools> schoolsPage = new Page<>(current,limit);
        QueryWrapper<Schools> wrapper = new QueryWrapper<>();
        wrapper.eq("region",province);
        schoolsService.page(schoolsPage,wrapper);
        List<Schools> records = schoolsPage.getRecords();
        long total = schoolsPage.getTotal();
        return Res.ok().data("page",records).data("total",total);
    }
}

