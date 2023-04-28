package com.xhu.javaprobaby.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ren
 * @since 2023-03-28
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysDiscuss implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 亲友评论id
     */
      @TableId(value = "discuss_id", type = IdType.AUTO)
    private Integer discussId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户身份
     */
    private String identity;

    /**
     * 父id
     */
    private Integer parentId;

    /**
     * 被回复人id
     */
    @TableField(value = "rep_user_id")
    private Integer repUserId;

    /**
     * 被回复人身份
     */
    private String repIdentity;

    /**
     * 动态id
     */
    private Integer trendId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8",pattern = "yyyy-MM-dd HH:mm")
    private Date discussTime;

    public SysDiscuss() {
    }

    public SysDiscuss(Integer discussId, Integer userId, String identity, Integer parentId, Integer repUserId, String repIdentity, Integer trendId, String content, Date discussTime) {
        this.discussId = discussId;
        this.userId = userId;
        this.identity = identity;
        this.parentId = parentId;
        this.repUserId = repUserId;
        this.repIdentity = repIdentity;
        this.trendId = trendId;
        this.content = content;
        this.discussTime = discussTime;
    }
}
