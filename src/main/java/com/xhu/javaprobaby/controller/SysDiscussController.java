package com.xhu.javaprobaby.controller;


import com.alibaba.fastjson.JSON;
import com.vdurmont.emoji.EmojiParser;
import com.xhu.javaprobaby.common.Result;
import com.xhu.javaprobaby.pojo.SysDiscuss;
import com.xhu.javaprobaby.pojo.SysIdentity;
import com.xhu.javaprobaby.service.impl.SysCommentServiceImpl;
import com.xhu.javaprobaby.service.impl.SysDiscussServiceImpl;
import com.xhu.javaprobaby.service.impl.SysIdentityServiceImpl;
import com.xhu.javaprobaby.service.impl.SysUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ren
 * @since 2023-03-28
 */
@RestController
@RequestMapping("/javaprobaby/discuss")
public class SysDiscussController {

    @Autowired
    SysDiscussServiceImpl discussService;

    @Autowired
    SysIdentityServiceImpl identityService;

    @Autowired
    SysCommentServiceImpl commentService;

    @Autowired
    SysUserServiceImpl userService;


    @RequestMapping("/save")
    public Result insert(@RequestBody Map<String,Object> data){
        try {
            //获取动态trend对象和标签tags集合
            SysDiscuss dis = JSON.parseObject(JSON.toJSONString(data.get("discuss")), SysDiscuss.class);
            Integer babyId = (Integer) data.get("babyId");

            //获取评论用户的身份
            List<SysIdentity> list = identityService.listIdentity(babyId);
            List<SysIdentity> identities = list.stream().filter(sysIdentity ->
                    sysIdentity.getUserId() == dis.getUserId()).collect(Collectors.toList());
            SysIdentity identity = identities.get(0);
            dis.setIdentity(identity.getIdentity());
            dis.setDiscussTime(new Date());


            //插入评论数据
            int count = discussService.insert(dis);
            if(count>0){
                dis.setContent(EmojiParser.parseToUnicode(dis.getContent()));//转回string字符串
            }

            return Result.success(dis);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("系统内部错误");
        }
    }


    /**
     * 删除评论
     * @param discussId
     * @return
     */
    @RequestMapping("/remove")
    public Result remove(@RequestParam("discussId") Integer discussId){
        boolean flag = discussService.removeById(discussId);
        if(flag){
            return Result.success("评论删除成功");
        }
        return Result.fail("删除失败");
    }

}

