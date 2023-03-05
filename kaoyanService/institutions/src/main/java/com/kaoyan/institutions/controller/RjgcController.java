package com.kaoyan.institutions.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.kaoyan.commonUtils.Res;
import com.kaoyan.institutions.entity.Rjgc;
import com.kaoyan.institutions.service.RjgcService;
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
 * @since 2022-12-30
 */
@RestController
@RequestMapping("/institutions/rjgc")
public class RjgcController {
    @Autowired
    RjgcService rjgcService;

    @RequestMapping("getBySchool/{academy}")
    public Res getBySchool(@PathVariable String academy){
        QueryWrapper<Rjgc> rjgcQueryWrapper = new QueryWrapper<>();
        rjgcQueryWrapper.eq("academy",academy);
        List<Rjgc> list = rjgcService.list(rjgcQueryWrapper);
        return Res.ok().data("list",list);
    }
}

