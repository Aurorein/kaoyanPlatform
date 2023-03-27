package com.kaoyan.institutions.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kaoyan.commonUtils.Res;
import com.kaoyan.institutions.entity.Kaoyandata;
import com.kaoyan.institutions.service.KaoyandataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cxn
 * @since 2022-12-19
 */
@RestController
@RequestMapping("/api/institutions/kaoyandata")
public class KaoyandataController {

    @Autowired
    KaoyandataService kaoyandataService;

    @GetMapping("getAlls")
    public List<Kaoyandata> findAll(){
        return kaoyandataService.list(null);
    }

//    type为1即学硕，type为0即专硕
    @RequestMapping("get/{school}/{year}/{type}")
    public Res get(@PathVariable String school, @PathVariable int year,@PathVariable int type){
        QueryWrapper<Kaoyandata> kaoyandataQueryWrapper = new QueryWrapper<>();
        kaoyandataQueryWrapper.eq("school_name",school);
        kaoyandataQueryWrapper.eq("the_year",year);
        if(type == 1){
            kaoyandataQueryWrapper.eq("xs_zs","xs");
        }else{
            kaoyandataQueryWrapper.eq("xs_zs","zs");
        }
        List<Kaoyandata> list = kaoyandataService.list(kaoyandataQueryWrapper);
        return Res.ok().data("list",list);

    }



}

