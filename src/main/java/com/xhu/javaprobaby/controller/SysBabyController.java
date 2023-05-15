package com.xhu.javaprobaby.controller;


import com.alibaba.fastjson.JSON;
import com.xhu.javaprobaby.common.Result;
import com.xhu.javaprobaby.pojo.*;
import com.xhu.javaprobaby.pojo.vo.BloodEnum;
import com.xhu.javaprobaby.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <p>
 * 宝宝信息表 前端控制器
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@RestController
@RequestMapping("/javaprobaby/baby")
public class SysBabyController {

    @Autowired
    SysBabyServiceImpl babyService;

    @Autowired
    SysIdentityServiceImpl identityService;

    @Autowired
    SysRecordServiceImpl recordService;


    @Autowired
    SysUserServiceImpl userService;

    @Autowired
    SysTrendServiceImpl trendService;

    @Autowired
    SysTrendTagServiceImpl trendTagService;

    @Autowired
    SysTagServiceImpl tagService;

    @Autowired
    SysFollowServiceImpl followService;

    @Autowired
    SysCollectServiceImpl collectService;

    @Autowired
    SysLikeServiceImpl likeService;

    @Autowired
    SysCommentServiceImpl commentService;


    /**
     * 添加宝宝
     * @param data 数据，包括baby 和identity家长身份
     * @return
     */
    @RequestMapping("/save")
    @Transactional
    public Result save(@RequestBody Map<String, Object> data){
        try {
            //获取baby对象
            SysBaby baby = JSON.parseObject(JSON.toJSONString(data.get("baby")), SysBaby.class);
            //增加宝宝
            babyService.insert(baby);

            //获取identity对象
            SysIdentity identity = JSON.parseObject(JSON.toJSONString(data.get("identity")), SysIdentity.class);
            identity.setBabyId(baby.getBabyId());

            //增加家长身份
            boolean flag = identityService.save(identity);
            if(flag){
                return Result.success("添加宝宝成功",baby);
            }
        }catch (Exception e){
            e.printStackTrace();
            //保证异常捕获之后事务还会回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error("系统内部错误");
        }
        return Result.fail("添加宝宝失败");
    }


    /**
     * 根据用户id查询出用户的所有宝宝
     * @param userId 用户id
     * @return
     */
    @RequestMapping("/listBabyByUserId")
    public Result listBabyByUserId(@RequestParam("userId") Integer userId){
        List<SysBaby> babies = babyService.listBabyByUserId(userId);
        return Result.success(babies);
    }

    /**
     * 根据宝宝id获取宝宝信息以及血型枚举型集合
     * @param babyId
     * @return
     */
    @RequestMapping("/getbaby")
    public Result getBaby(@RequestParam("babyId") Integer babyId){
        SysBaby baby = babyService.getById(babyId);
        List<String> bloods = BloodEnum.getBlood();
        Map<String,Object> map = new HashMap<>();
        map.put("baby",baby);
        map.put("bloods",bloods);
        return Result.success(map);
    }

    /**
     * 修改宝宝信息
     * @param baby
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody SysBaby baby){
        try {
            baby.setModifiedTime(new Date());
            int count = babyService.updateBaby(baby);
            if (count > 0) {
                return Result.success("修改成功", baby);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("系统内部错误");
        }
        return Result.fail("宝宝信息修改失败");
    }

    /**
     * 删除宝宝
     * @param babyId
     * @return
     */
    @RequestMapping("/remove")
    @Transactional
    public Result remove(Integer babyId){
        try {
            boolean flag = babyService.removeById(babyId);
            if (flag) {
                //判断是否存在身份信息，存在则删除身份信息
                List<SysIdentity> list1 = identityService.listIdentity(babyId);
                if (list1.size() > 0) {
                    List<Integer> ids = new ArrayList<>();
                    for (SysIdentity i : list1) {
                        ids.add(i.getIdentityId());
                    }
                    identityService.removeByIds(ids);
                }

                //判断是否存在宝宝生长记录，存在则删除
                List<SysRecord> list2 = recordService.listRecord(babyId);
                if (list2.size() > 0) {
                    List<Integer> ids = new ArrayList<>();
                    for (SysRecord re : list2) {
                        ids.add(re.getRecordId());
                    }
                    recordService.removeByIds(ids);
                }

                //判断是否存在宝宝相关的动态，存在则删除
                List<Integer> trendIds = trendService.listByBabyId(babyId);
                if (trendIds.size() > 0) {
                    trendService.removeByIds(trendIds);
                }
                for (Integer trendId : trendIds) {
                    //查询是否包含标签、收藏信息、点赞信息、评论信息，若存在，则删除
                    //删除标签
                    List<SysTrendTag> list3 = trendTagService.listTrendTag(trendId);
                    if (list3.size() > 0) {
                        List<Integer> ids = new ArrayList<>();
                        for (SysTrendTag t : list3) {
                            ids.add(t.getId());
                        }
                        trendTagService.removeByIds(ids);
                    }

                    //删除收藏
                    List<SysCollect> list4 = collectService.listCollect(trendId);
                    if (list4.size() > 0) {
                        List<Integer> ids = new ArrayList<>();
                        for (SysCollect t : list4) {
                            ids.add(t.getCollectId());
                        }
                        collectService.removeByIds(ids);
                    }

                    //删除点赞
                    List<SysLike> list5 = likeService.listLike(trendId);
                    if (list5.size() > 0) {
                        List<Integer> ids = new ArrayList<>();
                        for (SysLike t : list5) {
                            ids.add(t.getLikeId());
                        }
                        likeService.removeByIds(ids);
                    }

                    //删除评论
                    List<SysComment> list6 = commentService.listComment2(trendId);
                    if (list6.size() > 0) {
                        List<Integer> ids = new ArrayList<>();
                        for (SysComment t : list6) {
                            ids.add(t.getCommentId());
                        }
                        commentService.removeByIds(ids);
                    }

                }

                return Result.success("删除成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            //保证异常捕获之后事务还会回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error("系统内部错误");
        }

        return Result.fail("删除宝宝失败");
    }


    /**
     * 删除宝宝：亲友删除宝宝（删除亲友关系）
     * @param babyId
     * @param userId
     * @return
     */
    @RequestMapping("/removeIdentity")
    public Result removeIdentity(@RequestParam("babyId") Integer babyId,
                                 @RequestParam("userId") Integer userId){
        int count = identityService.removeIdentity(babyId, userId);
        if(count>0){
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }


}

