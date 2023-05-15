package com.xhu.javaprobaby.controller;


import com.xhu.javaprobaby.common.Result;
import com.xhu.javaprobaby.pojo.SysFollow;
import com.xhu.javaprobaby.service.impl.SysFollowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 关注表 前端控制器
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@RestController
@RequestMapping("/javaprobaby/follow")
public class SysFollowController {

    @Autowired
    SysFollowServiceImpl followService;


    /**
     * 关注
     * @param follow
     * @return
     */
    @RequestMapping("/save")
    public Result save(@RequestBody SysFollow follow){
        boolean flag = followService.save(follow);
        if(flag){
            return Result.success("关注成功");
        }
        return Result.fail("关注失败");
    }

    /**
     * 取消关注
     * @param followerId
     * @param concernedId
     * @return
     */
    @RequestMapping("/remove")
    public Result remove(@RequestParam("followerId") Integer followerId,
                         @RequestParam("concernedId") Integer concernedId){
        int count = followService.remove(followerId, concernedId);
        if(count>0){
            return Result.success("取关成功");
        }
        return Result.fail("取消关注失败");
    }

}

