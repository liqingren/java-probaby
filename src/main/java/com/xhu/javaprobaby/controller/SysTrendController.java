package com.xhu.javaprobaby.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.vdurmont.emoji.EmojiParser;
import com.xhu.javaprobaby.common.*;
import com.xhu.javaprobaby.pojo.*;
import com.xhu.javaprobaby.pojo.vo.*;
import com.xhu.javaprobaby.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 动态表 前端控制器
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@RestController
@RequestMapping("/javaprobaby/trend")
public class SysTrendController{

    @Autowired
    SysUserServiceImpl userService;

    @Autowired
    SysBabyServiceImpl babyService;

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

    @Autowired
    SysDiscussServiceImpl discussService;

    @Autowired
    SysIdentityServiceImpl identityService;


    /**
     * 获取level枚举类的value值
     * @return
     */
    @RequestMapping("/getlevel")
    public Result getLevel(){
        return Result.success(LevelEnum.getLevel());
    }


    /**
     * 增加动态
     * @param data
     * @return
     */
    @RequestMapping("/save")
    @Transactional
    public Result save(@RequestBody Map<String,Object> data){
        try {
            //获取动态trend对象和标签tags集合
            SysTrend trend = JSON.parseObject(JSON.toJSONString(data.get("trend")), SysTrend.class);
            List<SysTag> tags = JSONArray.parseArray(JSON.toJSONString(data.get("tags")), SysTag.class);

            int count = trendService.insert(trend);
            if (count>0) {
                //获取动态标签数据
                List<SysTrendTag> list1 = new ArrayList<>();
                for(SysTag tag:tags){
                    SysTrendTag trendTag = SysTrendTag.builder()
                            .tagId(tag.getTagId())
                            .trendId(trend.getTrendId())
                            .build();
                    list1.add(trendTag);
                }
                for(SysTrendTag tag:list1){
                    trendTagService.save(tag);
                }

                return Result.success("动态上传成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            //保证异常捕获之后事务还会回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error("系统内部错误");
        }
        return Result.fail("动态上传失败");
    }

    /**
     * 根据宝宝id和用户id获取宝宝的记录动态
     * @param babyId
     * @return
     */
    @RequestMapping("/listtrend")
    public Result listTrend(@RequestParam("babyId") Integer babyId,@RequestParam("userId") Integer userId){
        List<TrendVO> list = trendService.listTrend(babyId,userId);
        for(TrendVO t:list){
            //转码内容（可能含emoji表情包）
            if(t.getTrendContent() != null && !t.getTrendContent().equals("")) {
                t.setTrendContent(EmojiParser.parseToUnicode(t.getTrendContent()));
            }

            //亲友间的评论
            List<SysDiscuss> discussList = discussService.listDiscuss(t.getTrendId());
            t.setDiscusses(discussList);

        }
        List<RealtiveVO> list2 = identityService.list(babyId);
        Map<String,Object> map = new HashMap<>();
        map.put("trend",list);
        map.put("count",list2.size());

        return Result.success(map);
    }

    /**
     * 根据动态id获取首页动态详情
     * @param trendId
     * @return
     */
    @RequestMapping("/gettrendByIndex")
    public Result getTrendByIndex(@RequestParam("trendId") Integer trendId){
        TrendVO trend = trendService.gettrendByIndex(trendId);

        //转码内容（可能含emoji表情包）
        if(trend.getTrendContent() != null && !trend.getTrendContent().equals("")) {
            trend.setTrendContent(EmojiParser.parseToUnicode(trend.getTrendContent()));
        }
        //标签
        List<SysTag> sysTags = tagService.listTags(trendId);
        trend.setTags(sysTags);
        /**
         * 亲友间的评论
         */
        List<SysDiscuss> discussList = discussService.listDiscuss(trendId);
        trend.setDiscusses(discussList);

        return Result.success("成功",trend);
    }



    /**
     * 获取自己有权限查看的所有动态
     * @param userId
     * @return
     */
    @RequestMapping("/list")
    public Result listTrends(@RequestParam("userId") Integer userId){
        List<TrendVO> list = trendService.listTrends(userId);
        for(TrendVO t:list){
            //转码内容（可能含emoji表情包）
            if(t.getTrendContent() != null && !t.getTrendContent().equals("")) {
                t.setTrendContent(EmojiParser.parseToUnicode(t.getTrendContent()));
            }

            //宝宝信息
            SysBaby baby = babyService.getById(t.getBabyId());
            t.setBaby(baby);

            //标签
            List<SysTag> sysTags = tagService.listTags(t.getTrendId());
            t.setTags(sysTags);

            //用户关注情况
            List<SysFollow> follows = followService.listFollow(userId);
            t.setFollows(follows);

            //动态的收藏情况
            List<SysCollect> collects = collectService.listCollect(t.getTrendId());
            t.setCollects(collects);

            //动态的点赞情况
            List<SysLike> likes = likeService.listLike(t.getTrendId());
            t.setLikes(likes);

            //动态的评论
            List<CommentVO> comments = commentService.listComment(t.getTrendId());
            t.setComments(comments);

        }
        return Result.success(list);
    }


    /**
     * 社区动态详情
     * @param trendId
     * @return
     */
    @RequestMapping("/gettrend")
    public Result getTrend(@RequestParam("trendId") Integer trendId){
        TrendVO trend = trendService.getTrend(trendId);
        //转码内容（可能含emoji表情包）
        if(trend.getTrendContent() != null && !trend.getTrendContent().equals("")) {
            trend.setTrendContent(EmojiParser.parseToUnicode(trend.getTrendContent()));
        }

        //宝宝信息
        SysBaby baby = babyService.getById(trend.getBabyId());
        trend.setBaby(baby);

        //标签
        List<SysTag> sysTags = tagService.listTags(trendId);
        trend.setTags(sysTags);

        //动态的收藏情况
        List<SysCollect> collects = collectService.listCollect(trendId);
        trend.setCollects(collects);

        //动态的点赞情况
        List<SysLike> likes = likeService.listLike(trendId);
        trend.setLikes(likes);

        //动态的评论
        List<CommentVO> comments = commentService.listComment(trendId);
        trend.setComments(comments);

        return Result.success(trend);

    }


    /**
     * 获取用户的收藏点赞关注信息
     * @param userId
     * @return
     */
    @RequestMapping("/listByUserId")
    public Result listByUserId(Integer userId){
        //收藏
        List<SysCollect> collects = collectService.listByUserId(userId);
        //点赞
        List<SysLike> likes = likeService.listByUserId(userId);
        //关注
        List<SysFollow> follows = followService.listFollow(userId);
        Map<String,Object> map= new HashMap<>();
        map.put("collects",collects);
        map.put("likes",likes);
        map.put("follows",follows);
        return Result.success(map);
    }


    /**
     * 用户收藏的动态信息
     * @param userId
     * @return
     */
    @RequestMapping("/listcollect")
    public Result listCollect(Integer userId){
        //用户的收藏
        List<SysCollect> collects = collectService.listByUserId(userId);
        List<Integer> ids = new ArrayList<>();
        for(SysCollect col:collects){
            ids.add(col.getTrendId());
        }
        //所有动态
        List<TrendVO> list = trendService.listAll();

        //过滤：获取用户收藏的动态信息
        List<TrendVO> streams = list.stream().filter(trendVOTag ->
                ids.contains(trendVOTag.getTrendId())
        ).collect(Collectors.toList());

        for(TrendVO t:streams){
            //转码内容（可能含emoji表情包）
            if(t.getTrendContent() != null && !t.getTrendContent().equals("")) {
                t.setTrendContent(EmojiParser.parseToUnicode(t.getTrendContent()));
            }
            //标签
            List<SysTag> sysTags = tagService.listTags(t.getTrendId());
            t.setTags(sysTags);

            //用户关注情况
            List<SysFollow> follows = followService.listFollow(userId);
            t.setFollows(follows);

            //动态的收藏情况
            List<SysCollect> collectList = collectService.listCollect(t.getTrendId());
            t.setCollects(collectList);

            //动态的点赞情况
            List<SysLike> likes = likeService.listLike(t.getTrendId());
            t.setLikes(likes);

            //动态的评论
            List<CommentVO> comments = commentService.listComment(t.getTrendId());
            t.setComments(comments);

        }
        return Result.success(streams);
    }


    /**
     * 用户点赞的动态信息
     * @param userId
     * @return
     */
    @RequestMapping("/listlike")
    public Result listLike(Integer userId){
        //用户的点赞
        List<SysLike> likes = likeService.listByUserId(userId);
        List<Integer> ids = new ArrayList<>();
        for(SysLike like:likes){
            ids.add(like.getTrendId());
        }

        //所有动态
        List<TrendVO> list = trendService.listAll();

        //过滤：获取用户点赞的动态
        List<TrendVO> streams = list.stream().filter(trendVOTag ->
                ids.contains(trendVOTag.getTrendId())
        ).collect(Collectors.toList());


        for(TrendVO t:streams){
            //转码内容（可能含emoji表情包）
            if(t.getTrendContent() != null && !t.getTrendContent().equals("")) {
                t.setTrendContent(EmojiParser.parseToUnicode(t.getTrendContent()));
            }
            //标签
            List<SysTag> sysTags = tagService.listTags(t.getTrendId());
            t.setTags(sysTags);
            //用户关注情况
            List<SysFollow> follows = followService.listFollow(userId);
            t.setFollows(follows);
            //动态的收藏情况
            List<SysCollect> collectList = collectService.listCollect(t.getTrendId());
            t.setCollects(collectList);
            //动态的点赞情况
            List<SysLike> likeList = likeService.listLike(t.getTrendId());
            t.setLikes(likeList);
            //动态的评论
            List<CommentVO> comments = commentService.listComment(t.getTrendId());
            t.setComments(comments);

        }
        return Result.success(streams);
    }


    /**
     * 删除动态
     * @param trendId
     * @return
     */
    @RequestMapping("/remove")
    @Transactional
    public Result remove(@RequestParam("trendId") Integer trendId){
        try {
            boolean flag = trendService.removeById(trendId);
            if (flag) {
                //查询是否包含标签、收藏信息、点赞信息、评论信息，若存在，则删除
                //删除标签
                List<SysTrendTag> list1 = trendTagService.listTrendTag(trendId);
                if (list1.size() > 0) {
                    List<Integer> ids = new ArrayList<>();
                    for (SysTrendTag t : list1) {
                        ids.add(t.getId());
                    }
                    trendTagService.removeByIds(ids);
                }

                //删除收藏
                List<SysCollect> list2 = collectService.listCollect(trendId);
                if (list2.size() > 0) {
                    List<Integer> ids = new ArrayList<>();
                    for (SysCollect t : list2) {
                        ids.add(t.getCollectId());
                    }
                    collectService.removeByIds(ids);
                }

                //删除点赞
                List<SysLike> list3 = likeService.listLike(trendId);
                if (list3.size() > 0) {
                    List<Integer> ids = new ArrayList<>();
                    for (SysLike t : list3) {
                        ids.add(t.getLikeId());
                    }
                    likeService.removeByIds(ids);
                }

                //删除评论
                List<SysComment> list4 = commentService.listComment2(trendId);
                if (list4.size() > 0) {
                    List<Integer> ids = new ArrayList<>();
                    for (SysComment t : list4) {
                        ids.add(t.getCommentId());
                    }
                    commentService.removeByIds(ids);
                }

                return Result.success("删除成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            //保证异常捕获之后事务还会回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error("系统内部错误");
        }

        return Result.fail("删除失败");
    }


    /**
     * 修改动态
     * @param data
     * @return
     */
    @RequestMapping("/update")
    @Transactional
    public Result update(@RequestBody Map<String,Object> data){
        try {
            //获取动态trend对象和标签tags集合
            SysTrend trend = JSON.parseObject(JSON.toJSONString(data.get("trend")), SysTrend.class);
            List<SysTag> tags = JSONArray.parseArray(JSON.toJSONString(data.get("tags")), SysTag.class);

            //修改时间
            trend.setModifiedTime(new Date());
            trendService.updateTrend(trend);

            //判断修改后的标签集合是否有内容，若有，则重新插入动态-标签数据，若没有，则将原有动态-标签数据删除
            if(tags.size()>0){
                //有内容
                List<SysTag> tagList = tagService.listTags(trend.getTrendId());
                boolean flag = tagList.equals(tags);
                //判断修改后的动态包含的标签与原来动态包含的标签集合是否相同
                if (!flag) {
                    //若不同，则需要先删除动态-标签表中的数据
                    trendTagService.remove(trend.getTrendId());
                    //获取动态标签数据
                    List<SysTrendTag> list2 = new ArrayList<>();
                    for (SysTag tag : tags) {
                        SysTrendTag trendTag = SysTrendTag.builder()
                                .tagId(tag.getTagId())
                                .trendId(trend.getTrendId())
                                .build();
                        list2.add(trendTag);
                    }
                    trendTagService.saveBatch(list2);//批量插入
                }
            }else{
                //没有内容，则需要先删除动态-标签表中的数据
                trendTagService.remove(trend.getTrendId());
            }


            return Result.success("动态修改成功");

        }catch (Exception e){
            e.printStackTrace();
            //保证异常捕获之后事务还会回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error("系统内部错误");
        }
    }


    /**
     * 大事记的动态
     * @param babyId
     * @return
     */
    @RequestMapping("/bigevents")
    public Result bigEvents(@RequestParam("babyId") Integer babyId,@RequestParam("userId") Integer userId){
        List<TrendVO> list = trendService.listTrend(babyId,userId);

        //动态-标签关系表
        List<SysTrendTag> trendTags = trendTagService.listFirst();
        List<Integer> trendIds = new ArrayList<>();
        for(SysTrendTag t:trendTags){
            trendIds.add(t.getTrendId());
        }
        //过滤：获取有包含“第一次”的标签的动态
        List<TrendVO> trends = list.stream().filter(trendVO ->
                trendIds.contains(trendVO.getTrendId())).collect(Collectors.toList());

        for(TrendVO t:trends){
            //转码内容（可能含emoji表情包）
            if(t.getTrendContent() != null && !t.getTrendContent().equals("")) {
                t.setTrendContent(EmojiParser.parseToUnicode(t.getTrendContent()));
            }

            //标签
            List<SysTag> sysTags = tagService.listTags(t.getTrendId());
            t.setTags(sysTags);

            //动态的评论
            List<SysDiscuss> discusses = discussService.listDiscuss(t.getTrendId());
            t.setDiscusses(discusses);

        }

        return Result.success(trends);
    }


}

