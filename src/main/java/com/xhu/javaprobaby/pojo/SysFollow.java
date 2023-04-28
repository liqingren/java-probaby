package com.xhu.javaprobaby.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 关注表
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysFollow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "follow_id", type = IdType.AUTO)
    private Integer followId;

    /**
     * 关注人id
     */
    private Integer followerId;

    /**
     * 被关注人id
     */
    private Integer concernedId;

    public SysFollow() {
    }

    public SysFollow(Integer followId, Integer followerId, Integer concernedId) {
        this.followId = followId;
        this.followerId = followerId;
        this.concernedId = concernedId;
    }
}
