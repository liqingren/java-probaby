package com.xhu.javaprobaby.controller;


import com.xhu.javaprobaby.common.Result;
import com.xhu.javaprobaby.pojo.*;
import com.xhu.javaprobaby.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 收藏表 前端控制器
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@RestController
@RequestMapping("/javaprobaby/collect")
public class SysCollectController {

    @Autowired
    SysCollectServiceImpl collectService;


    /**
     * 收藏
     * @param collect
     * @return
     */
    @RequestMapping("/save")
    public Result save(@RequestBody SysCollect collect){
        boolean flag = collectService.save(collect);
        if(flag){
            return Result.success("收藏成功");
        }
        return Result.fail("收藏失败");
    }

    /**
     * 取消收藏
     * @param userId
     * @param trendId
     * @return
     */
    @RequestMapping("/remove")
    public Result remove(@RequestParam("userId") Integer userId,
                         @RequestParam("trendId") Integer trendId){
        int count = collectService.removeCollect(userId, trendId);
        if(count>0){
            return Result.success();
        }
        return Result.fail("取消收藏失败");
    }





}

