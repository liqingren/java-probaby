package com.xhu.javaprobaby.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xhu.javaprobaby.pojo.*;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class TrendVO {

    private static final long serialVersionUID = 1L;

    /**
     * 动态id
     */
    private Integer trendId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户头像
     */
    private String userPhoto;

    /**
     * 宝宝id
     */
    private Integer babyId;

    /**
     * 谁可以看（0：所有人，1：仅自己）
     */
    private Integer level;

    /**
     * 动态内容
     */
    private String trendContent;

    /**
     * 动态照片
     */
    private String trendPhoto;

    /**
     * 动态视频
     */
    private String trendVideo;

    /**
     * 上传时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8",pattern = "yyyy-MM-dd")
    private Date uploadTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8",pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;


    /**
     * 用户是宝宝的什么家长（爸爸、妈妈）
     */
    private String identity;

    /**
     * 宝宝信息
     */
    private SysBaby baby;

    /**
     * 动态包含的标签集合
     */
    List<SysTag> tags;

    /**
     * 关注集合
     */
    List<SysFollow> follows;

    /**
     * 收藏集合
     */
    List<SysCollect> collects;

    /**
     * 评论集合
     */
    List<CommentVO> comments;

    /**
     * 亲友间的评论
     */
    List<SysDiscuss> discusses;

    /**
     * 点赞集合
     */
    List<SysLike> likes;

    public TrendVO() {
    }

    public TrendVO(Integer trendId, Integer userId, String username, String userPhoto, Integer babyId, Integer level, String trendContent, String trendPhoto, String trendVideo, Date uploadTime, Date createTime, String identity, SysBaby baby, List<SysTag> tags, List<SysFollow> follows, List<SysCollect> collects, List<CommentVO> comments, List<SysDiscuss> discusses, List<SysLike> likes) {
        this.trendId = trendId;
        this.userId = userId;
        this.username = username;
        this.userPhoto = userPhoto;
        this.babyId = babyId;
        this.level = level;
        this.trendContent = trendContent;
        this.trendPhoto = trendPhoto;
        this.trendVideo = trendVideo;
        this.uploadTime = uploadTime;
        this.createTime = createTime;
        this.identity = identity;
        this.baby = baby;
        this.tags = tags;
        this.follows = follows;
        this.collects = collects;
        this.comments = comments;
        this.discusses = discusses;
        this.likes = likes;
    }
}
