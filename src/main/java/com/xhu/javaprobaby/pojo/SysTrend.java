package com.xhu.javaprobaby.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 动态表
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysTrend implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 动态id
     */
    @TableId(value = "trend_id", type = IdType.AUTO)
    private Integer trendId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 昵称
     */
    private String username;

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
    private String content;

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
    private Date uploadTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date modifiedTime;

    public SysTrend() {
    }

    public SysTrend(Integer trendId, Integer userId, String username, Integer babyId, Integer level, String content, String trendPhoto, String trendVideo, Date uploadTime, Date createTime, Date modifiedTime) {
        this.trendId = trendId;
        this.userId = userId;
        this.username = username;
        this.babyId = babyId;
        this.level = level;
        this.content = content;
        this.trendPhoto = trendPhoto;
        this.trendVideo = trendVideo;
        this.uploadTime = uploadTime;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
    }
}
