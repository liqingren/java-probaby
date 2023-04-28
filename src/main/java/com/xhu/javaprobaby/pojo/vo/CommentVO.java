package com.xhu.javaprobaby.pojo.vo;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class CommentVO {
    /**
     * 评论id
     */
    private Integer commentId;

    /**
     * 评论人id
     */
    private Integer userId;

    /**
     * 评论人昵称
     */
    private String username;

    /**
     * 评论人头像
     */
    private String userPhoto;


    /**
     * 动态id
     */
    private Integer trendId;

    /**
     * 内容
     */
    private String content;

    /**
     * 评论图片
     */
    private String media;

    /**
     * 评论时间
     */
    private String commentTime;

    /**
     * 评论数量
     */
    private Integer count;

    /**
     * 父id
     */
    private Integer parentId;


    /**
     * 子级
     */
    private List<CommentVO> children;

    public CommentVO() {
        children = new ArrayList<>();
    }

    public CommentVO(Integer commentId, Integer userId, String username, String userPhoto, Integer trendId, String content, String media, String commentTime, Integer count, Integer parentId, List<CommentVO> children) {
        this.commentId = commentId;
        this.userId = userId;
        this.username = username;
        this.userPhoto = userPhoto;
        this.trendId = trendId;
        this.content = content;
        this.media = media;
        this.commentTime = commentTime;
        this.count = count;
        this.parentId = parentId;
        this.children = children;
    }
}
