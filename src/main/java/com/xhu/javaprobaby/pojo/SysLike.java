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
 * 点赞表
 * </p>
 *
 * @author ren
 * @since 2023-03-01
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysLike implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "like_id", type = IdType.AUTO)
    private Integer likeId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 动态id
     */
    private Integer trendId;

    public SysLike() {
    }

    public SysLike(Integer likeId, Integer userId, Integer trendId) {
        this.likeId = likeId;
        this.userId = userId;
        this.trendId = trendId;
    }
}
