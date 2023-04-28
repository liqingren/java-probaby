package com.xhu.javaprobaby.controller;


import com.xhu.javaprobaby.common.Result;
import com.xhu.javaprobaby.pojo.SysLike;
import com.xhu.javaprobaby.service.impl.SysLikeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 点赞表 前端控制器
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@RestController
@RequestMapping("/javaprobaby/like")
public class SysLikeController {

    @Autowired
    SysLikeServiceImpl likeService;

    /**
     * 点赞
     * @param like
     * @return
     */
    @RequestMapping("/save")
    public Result save(@RequestBody SysLike like){
        boolean flag = likeService.save(like);
        if(flag){
            return Result.success("点赞成功");
        }
        return Result.fail("点赞失败");
    }


    /**
     * 取消点赞
     * @param userId
     * @param trendId
     * @return
     */
    @RequestMapping("/remove")
    public Result remove(@RequestParam("userId") Integer userId,@RequestParam("trendId") Integer trendId){
        int count = likeService.removeLike(userId, trendId);
        if(count>0){
            return Result.success();
        }
        return Result.fail("取消点赞失败");
    }





}

