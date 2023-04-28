package com.xhu.javaprobaby.pojo;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.ArrayList;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 评论表
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
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
     * 父id
     */
    private Integer parentId;

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
    @JsonFormat(locale="zh", timezone="GMT+8",pattern = "yyyy-MM-dd HH:mm")
    private Date commentTime;

    /**
     * 子级
     */
    private List<SysComment> children;

    public SysComment() {
        children = new ArrayList<>();
    }

    public SysComment(Integer commentId, Integer userId, String username, String userPhoto, Integer parentId, Integer trendId, String content, String media, Date commentTime, List<SysComment> children) {
        this.commentId = commentId;
        this.userId = userId;
        this.username = username;
        this.userPhoto = userPhoto;
        this.parentId = parentId;
        this.trendId = trendId;
        this.content = content;
        this.media = media;
        this.commentTime = commentTime;
        this.children = children;
    }
}
