package com.xhu.javaprobaby.controller;


import com.xhu.javaprobaby.common.Result;
import com.xhu.javaprobaby.service.impl.SysRegionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;

/**
 * <p>
 * 区域表 前端控制器
 * </p>
 *
 * @author ren
 * @since 2023-05-04
 */
@RestController
@RequestMapping("/javaprobaby/region")
public class SysRegionController {

    @Autowired
    SysRegionServiceImpl regionService;


    /**
     * 获取省市二级地区
     * @return
     */
    @RequestMapping("/listregion")
    public Result listRegion(){
        return Result.success(regionService.listRegion());
    }

}

